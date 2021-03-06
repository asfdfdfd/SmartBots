Include "cfg.bb"
;=================================
Global NameBot$
Const MaxVariables=90
;=================================
Const DS=4
Global glStack = CreateBank(DS*200)
Global glTop%
Global CurrScript.Script
;=================================
Type Script
    Field name$
	Field s ; ���� � �����
	Field pos%
	Field model$
	Field corpus%
	Field radar%
	;vars
	Field var ; ���� � �����������
	Field intVarN$[MaxVariables] ; ���
	Field intVarC
	;debug
	Field time
	Field limit
	Field ErrorID%
	Field ErrorTag$
End Type

;Type Expr
;	Field pTrue% ; ����� ������, ��� ������ ���� ������ �� �����
;	Field pFalse% ; ����� ������, ��� ������ ���� ������ �� �����
;	Field pType ; 1 - if, 2 - while ; 3 - for
;	Field name$
;End Type
;================================
Function AddVarToScript(s.script, name$)
	s\intVarC=s\intVarC+1
	s\intVarN[s\intVarC]=name
End Function

Function GetVarValue#(s.script, num%)
	Return PeekFloat(s\var, (num-1)*4)
End Function

Function SetVarValue(s.script, num%, value#)
	PokeFloat s\var, (num-1)*4, value
End Function

Function SetVarValueInt(s.script, num%, value%)
	PokeInt s\var, (num-1)*4, value
End Function

Function CreateScript.script(name$)
	s.script=New script
	s\name$=name$
	s\var=CreateBank() ; ����������
	s\s=CreateBank() ; ���
	s\limit=timelimit ; ����� �������
	s\intVarC=0 ; ���-�� ����������
	s\ErrorID=0 ; ����� ������
	f=ReadFile("cfg\import.cfg")
	While Not Eof(f)
		ss=Trim(ReadLine(f))
		p=Instr(ss, ";")
		ss=Mid(ss, 1, p-1)
		If ss<>"" AddVarToScript(s, ss)
	Wend
	CloseFile(f)
	Return s
End Function

Function DeleteAllScripts()
	For s.script=Each script
		FreeScript(s)
	Next
End Function

Function FreeScript(sc.script)
	FreeBank sc\s
	FreeBank sc\var
	Delete sc
End Function

;Function ReplaceExt$(name$, ext$)
;	If Left(ext, 1)<>"." ext="."+ext
;	pos=Instr(name, ".")
;	If pos>0 name=Left(name, pos-1)
;	Return name+ext
;End Function

Function LoadScript%(sc.script, name$)
	If FileType(name)<>1 Return 1
		f=ReadFile(name)
	If f<>0
		;version
		version$ = ReadLine ( f )
		If version <> ScriptVersion CloseFile f : Return
		;variables
		sc\intVarC = ReadInt(f)
		For k=1 To sc\intVarC
			sc\intVarN [ k ] = ReadLine(f)
		Next
		ResizeBank sc\var, sc\intVarC*4
		For k = 1 To BankSize(sc\var)
			PokeByte sc\var, k-1, 0
		Next
		;model
		sc\model=ReadLine(f)
		;name
		nameBot=ReadLine(f)
		;code
;		AddToLog ( "LoadScript "+name)
;		AddToLog ( "count of vars = "+sc\intVarC )
;		AddToLog("name = "+nameBot)
;		AddToLog("model = "+sc\model )
		ResizeBank sc\s, ReadInt(f)
		For k=1 To BankSize(sc\s)
			PokeByte sc\s, k-1, ReadByte(f)
		Next
	CloseFile f
Else
	 Return 1
EndIf
End Function

Function InterpeteScript(s.script,t.tank)
currScript=s
count=BankSize(s\s)/4
k=1
glTop=0
LTime=MilliSecs()+timelimit
While (k<=count)
If MilliSecs()>LTime
messag(t.tank)
Return
EndIf
code%=PeekInt(s\s, (k-1)*4)
Select True
Case code=1 ; pop_
id%=PeekInt(s\s, k*4) ; ���������� ������ ����������
glTop=glTop-1
; glTop=0
buf%=PeekInt(glStack, glTop*4) ; ��������� �� ����� ����
PokeInt s\var, (id-1)*4, PeekInt(glStack, glTop*4) ; ������� ���� � ����������
k=k+1
Case code=2 ; push var
id%=PeekInt(s\s, k*4) ; ���������� ������ ����������
buf%=PeekInt(s\var, (id-1)*4) ; ��������� ���� �� ����������
PokeInt glStack, glTop*4, PeekInt(s\var, (id-1)*4) ; ������� � ����
glTop=glTop+1
k=k+1
Case code=3 ; push num
PokeFloat glStack, glTop*4, PeekFloat(s\s, k*4)
glTop=glTop+1
k=k+1
Case code=4 ; IF
glTop=glTop-1
glTop=0
If (PeekFloat(glStack, glTop*4)=0) k=PeekInt(s\s, k*4) Else k=k+1
Case code=5 ; goto_
k=PeekInt(s\s, k*4)
Case (code>=8) And (code<=26) ; 2 parameters
glTop=glTop-1
x#=PeekFloat(glStack, (glTop-1)*4)
y#=PeekFloat(glStack, (glTop)*4)
Select code
Case 8 : x = (x<y)
Case 9 : x = (x>y)
Case 10 : x = (x=y)
Case 11 : x = (x<>y)
Case 12 : x = (x<=y)
Case 13 : x = (x>=y)
Case 14 : x = (x+y)
Case 15 : x = (x-y)
Case 16 : x = (x*y)
Case 17
If y=0 currScript\ErrorID=10:Return
x = x/y
Case 18 :
If y=0 currScript\ErrorID=10:Return
x = Int(x) Mod Int(y)
Case 19 : x = min(x, y)
Case 20 : x = max(x, y)
Case 21 : x = Rnd(x, y)
Case 22 : x = Rand(Int(x), Int(y))
Case 23 : x = ATan2(x, y)
Case 24 : x = x And y
Case 25 : x = x Or y
Case 26 : x = x Or y
End Select
PokeFloat glStack, (glTop-1)*4, x
Case code=100 :
glTop=glTop-1
x#=PeekFloat(glStack, (glTop)*4)
ScanRadar ( x#,t.tank )
Case (code<=110) and (code>=108) ; Distance; Delta-Yaw,Pitch
glTop=glTop-1
buf%=PeekInt(glStack, (glTop-1)*4)
buf3%=PeekInt(glStack, (glTop)*4)
If (buf>1000) And (buf3>1000)
Select code
Case 108 :
y# = EntityDistance(buf, buf3)
PokeFloat glStack, (glTop-1)*4, y
Case 109 :
y# = DeltaYaw(buf, buf3)
PokeFloat glStack, (glTop-1)*4, y
Case 110 :
y# = DeltaPitch(buf, buf3)
PokeFloat glStack, (glTop-1)*4, y
End Select
End If
Case code=111 ; PointRadar
glTop=glTop-1
buf3%=PeekInt(glStack, (glTop)*4)
If buf3>1000 PointEntity s\radar, buf3
Case (code>=112) And (code<=126) ; Entity X-Y-Z-Pitch-Yaw-Roll
buf%=PeekInt(glStack, (glTop-1)*4)
If buf>1000
Select code
Case 112 : y# = EntityX(buf)
Case 113 : y# = EntityY(buf)
Case 114 : y# = EntityZ(buf)
Case 115 : y# = EntityPitch(buf)
Case 116 : y# = EntityYaw(buf)
Case 117 : y# = EntityRoll(buf)
Case 118 : y# = CollisionX(buf, 1)
Case 119 : y# = CollisionY(buf, 1)
Case 120 : y# = CollisionZ(buf, 1)
Case 121 : y# = CollisionNX(buf, 1)
Case 122 : y# = CollisionNY(buf, 1)
Case 123 : y# = CollisionNZ(buf, 1)
Case 124 : y# = TypePick(buf, s\corpus)
Case 125 : y# = Lives ( buf )
case 126 : y# = Entity_Visible(t.tank, buf)
End Select
PokeFloat glStack, (glTop-1)*4, y
End If
Case (code>=30) And (code<=36) ; unar functions
x#=PeekFloat(glStack, (glTop-1)*4)
Select code
Case 30 : x = Abs(x)
Case 31 : x = Sgn(x)
Case 32 : x = Sqr(x)
Case 33 : x = Sin(x)
Case 34 : x = Cos(x)
Case 35 : x = Not (x)
Case 36 : x = -x
End Select
PokeFloat glStack, (glTop-1)*4, x
End Select
k=k+1
Wend
End Function
;Function SendError(sc.script, id, tag$)
;	sc\errorID=id
;	sc\errorTag=tag
;End Function

Function InitScript$(t.tank, name$) ; name - ��� �������, .ai - ��� ���������������, .txt - ��������� ( �������� ������� )
	t\sc=CreateScript(name$)
	kon=LoadScript(t\sc, name)
	If kon=0
	  Return t\sc\model
	Else
	  FreeScript(t\sc)
	  Return "faled"
	EndIf
End Function
;+++++++++++++ ��� ������� ���������� � �������� ��� ������� ���� +++++++++++++++++++++++++++++
Function InitTankScripts ( )
	For t.tank = Each tank
	If t\name$<>namePl$
		SetVarValueInt(t\sc, 30, t\pivot) ; pivot
		SetVarValueInt(t\sc, 31, t\turret) ; turret
		SetVarValueInt(t\sc, 32, t\stvol) ; Trunk
		SetVarValueInt(t\sc, 33, t\corpus) ; Corpus
		SetVarValueInt(t\sc, 34, centr) ; Center
		
		t\sc\corpus = t\corpus ; corpus
		t\sc\radar  = t\pivot ; radar
	EndIf
	Next
End Function

Function UpdateScript(t.tank)
	
	
	SetVarValue(t\sc, 1, 0) ; move
	SetVarValue(t\sc, 2, 0) ; turncorpus
	SetVarValue(t\sc, 3, 0) ; turnturret
	SetVarValue(t\sc, 4, 0) ; turngun	
	SetVarValue(t\sc, 5, 0) ; fire1
	SetVarValue(t\sc, 6, 0) ; fire2
	
   SetVarValue(t\sc, 7, t\live) ;lives
	SetVarValue(t\sc, 8, t\bullet) ;bullet
	SetVarValue(t\sc, 9, t\rocket) ;rocket
	SetVarValue(t\sc, 10, command) ;typeGame
	SetVarValue(t\sc, 11, kolbot) ;HowManyBots
	SetVarValue(t\sc, 12, t\DamageYaw) ;DamageYaw
	
    

	
	SetVarValue(t\sc, 20, t\xc) ; CaseX
	SetVarValue(t\sc, 21, t\yc) ; CaseY
	SetVarValue(t\sc, 22, t\zc) ; CaseZ
	SetVarValue(t\sc, 23, t\rxc) ; CasePitch
	SetVarValue(t\sc, 24, t\ryc) ; CaseYaw
	SetVarValue(t\sc, 25, t\rzc) ; CaseRoll
	SetVarValue(t\sc, 26, t\ryt) ; TurretYaw
	SetVarValue(t\sc, 27, t\rxs) ; TrunkPitch
	
	SetVarValueInt(t\sc, 28, t\collignt) ; collided
	SetVarValueInt(t\sc, 29, t\collignW) ; collidedStruct
	
	
	InterpeteScript(t\sc,t.tank)
	
	t\move  = GetVarValue(t\sc, 1) ; move
	t\angl  = GetVarValue(t\sc, 2) ; turncorpus
	t\anglY = GetVarValue(t\sc, 3) ; turnturret
	t\anglX = GetVarValue(t\sc, 4) ; turngun
	t\fire  = GetVarValue(t\sc, 5) ; fire1
	t\fire2  = GetVarValue(t\sc, 6) ; fire2
	
	
End Function

Function messag(t.tank)
  mes=100
  mess$=t\name$
End Function

Function updatemessag()
  mes=mes-1
  Text wig/2-200,hei/2,mess$ +"   Has exceeded a limit of time !"
End Function