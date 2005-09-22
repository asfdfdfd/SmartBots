;ShowWindow SystemProperty("AppHWnd"),0



Global enemy,bullet,rocketb,repair,strukt#,struktAngle#,wallAngle#,wall#,rad,textPad,speedBullet,speedRocket

;====================
.nac
Include "radar.bb"
Include "scripts.bb"
Include "debugger.bb"
Include "functionD.bb"
Include "types.bb"
Include "Timelimit.bb"
Include "blitzsys.bb"
Global fpsSpeed
Global mencfg
Global FPS = 50  ;�������� ���� (�������� ��������)
Global period = 1000 / FPS

Global Time = MilliSecs () - period
Global wig#
Global hei#

Global bwig#
Global bhei#
Global game
Global shadL
.graf

Global timeTurM#
Global maxlive#   ;���� �����
Global  maxRocket ;���� �����
Global maxBullet ;���� ��������
Global max#    ;������������ �������� ������
Global pop#   ;�������� ����
Global speedfire#  ;����������������

Global steps#=10 ;�������� �������� ������
Global maxC#=8  ;������������ �������� �������� �����
Global maxU#=.2   ;������������ ��������� ��������
Global maxT#=6  ;������������ �������� �������� ������
Global maxG#=6  ;������������ �������� �������� ������
Global usk#=.2   ;������������ ���������

Global mes,mess$
Global otladka#
Global mscr,namebotp$
Global osk,oskT1,oskT2,oskT3,oskT4,oskS

Global sled,dome
Global terrain
Global texter#=64
Global timecontrol#

Global cctex
Global sledT
Global centr
Global command

Global maxosk
Global maxsmok

Global profile$;="lucky.cbp"
Global namePl$;="Ubisoft"
Global ModPlayer$;="hammer"
Global player,detP
Global kolprofile
Global mox#
Global moy#
Global winN$
Global win,yangM#
Global xang#,yang#,dest_xang#,dest_yang#,x#,z#
;---------------------tablo
Global red
Global blue
Global redtex
Global bluetex
Global Redpoint
Global bluepoint
Global tabAll
Global texAll
Global spriteAll
Global tabDown
Global texDown
Global spriteDown
Global  chall
Global timeTur#
;-----------------control
Global upr
Global moveT#
Global angleT#
Global fireK
Global fireR
Global  pivK
Global  Pcel
Global  Pcela
Global pric1
Global pric2
Global krest
Global krestS#
;Global pric
Global pricm
Global UprBullet
 Global UprRocket
Global UprLive


;----------------------sound
Global list
Global sgus,sgun,sbah,sbah3,swal,swalC,chanM,sBon,sRoc
;-------------------global menu
Global trubaf
Global profPan
Global TextureProf
Global inp
Global angPr#
Global spritef

Global namPP$

Global or1
Global or2
Global or3
Global or4
;-----------------------help
Global help$
Global bhelp$
Global helpsc#
Global helpMesh
Global TextHelp
Global spriteH
Global ef
Global helpme
Global esc
;----------sound
Global sbul
Global slet
Global smov
Global smov1
Global smov2
Global stel1
Global stel2
Global sHmov
;----------------
Global timeKn
Global knp
Global knl
Global ligmen
Global ligmen2
Global pivlig
Global kur
Global sledm
Global sleda
Global picked
Global kura
Global fon
Global truba
Global men$
Global bmen$
Global mexit
Global audS#
Global audSa#
Global effect
Global kolbot
Global kolAi
Global  tekAi
Global TekBot$
Global tankM
Global tekMod$
Global rotMod#
Global rotModb#
Global m1
Global m2
Global m3
Global m4
Global m5
Global m6
Global m7
Global m8
Global sh
Global pm1#
Global pm2#
Global pm3#
Global pm4#
Global pms1#
Global pms2#
Global pms3#
Global pms4#

Global Ykor#
Global korps
Global korpsP
Global korpsPa

Global Ylev#
Global levK
Global rot1
Global rot2
Global rot3
Global motor
Global reik
Global levp
Global screen
Global RlevP#
Global dlevP#
Global movLev#
Global movLevb#
Global fl
Global smec#=86

Global Level$
Global Mlev

Global Rocket
;-----------------------------------
load()



Global fx#=wig/1024
;-------------------------------------------------okno

;ShowWindow SystemProperty("AppHWND"),1
;----------------------------------------------------
Graphics3D wig,hei,32,1


intro=LoadImage("texture\intro.jpg")
DrawImage intro,wig/2-300,hei/2-150
Flip

;---------------------------------------------okno
;api_SW hWnd,0

Dim akka(9)
For i=0 To 9
 akka(i)=LoadImage("texture\"+i+".tga")
 If fx<>1
   ScaleImage akka(i),fx,fx
 EndIf
Next
Dim akkat(9)
For i=0 To 9
 akkat(i)=LoadImage("texture\0"+i+".tga")
  If fx<>1
   ScaleImage akkat(i),fx,fx
 EndIf

Next
imRoc=LoadImage("texture\roc.png")
imLiv=LoadImage("texture\liv.png")
ScaleImage imRoc,fx,fx
ScaleImage imLiv,fx,fx
;---------------------------------------------

Global xcur#=wig/2
Global ycur#=hei/2
SetBuffer BackBuffer()


;--------------------TimeLimit---------------------------
;If DLLBlitzSysInitialise() < 104 Then RuntimeError("blitzsys.dll not found or incorrect version! You suck! :)")

 Global timelimit#
  loadTM()

;----------------------------------------------------------
 textPad=LoadTexture("menu\M24.JPG")
;==============================

Include "Swift Shadow System - 037.bb"

;-------------------------------------
Global fon1=LoadFont("arial cyr",36,1)
Global fon2=LoadFont("arial cyr",32,1)
Global fon3=LoadFont("arial cyr",40,1)
Global fon4=LoadFont("arial cyr",24*fx,1)
Global fon5=LoadFont("arial cyr",16,1)
Global fon5a=LoadFont("arial cyr",16)
Global fon5b=LoadFont("arial cyr",14,1)
Global fon6=LoadFont("arial cyr",24)

Global cam=CreateCamera()

Global campiv=CreatePivot()
EntityType campiv,10
EntityRadius campiv,20
EntityType cam,10
EntityRadius cam,20
;----------------------------
Global bulletM
Global remontM
Global rocketM
;-------------------pricel
pric1=LoadMesh("menu\pr1.3ds")
pric2=LoadMesh("menu\pr2.3ds")
krest=LoadMesh("menu\kr.3ds")
EntityOrder pric1,-1
EntityOrder pric2,-1
EntityOrder krest,-1

Global pric=CreatePivot()
EntityParent pric1,pric
EntityParent pric2,pric
EntityParent krest,pric 

krestS=.004
ScaleEntity pric1,krestS,krestS,krestS
ScaleEntity pric2,krestS*1.2,krestS*1.2,krestS*1.2
ScaleEntity krest,krestS,krestS,krestS

EntityFX pric1,1
EntityFX pric2,1
EntityFX krest,1
EntityBlend pric1,3
EntityBlend pric2,3
EntityBlend krest,3

EntityColor pric1,0,255,0
EntityColor pric2,0,255,0
EntityColor krest,0,255,0


pricm=CopyEntity(pric)



PositionEntity pric,EntityX(cam,1),EntityY(cam,1),EntityZ(cam,1)
RotateEntity pric,EntityPitch(cam,1),EntityYaw(cam,1),EntityRoll(cam,1)
MoveEntity pric,0,0,12
EntityParent pric,cam
EntityAlpha pric1,.3
EntityAlpha pric2,.3
EntityAlpha krest,.3
HideEntity pric1
HideEntity pric2
HideEntity krest
;--------------------------------

;CameraRange cam,.1,5000
;light=CreateLight()
;RotateEntity light,60,20,0
Global pivot=CreatePivot()

;Global cube=CreateCube()
;ScaleEntity cube,.5,.5,.5
;EntityPickMode cube,2

;
;ScaleEntity cube,10,10,10
;EntityParent cube,pivot

;=====================createparticles
Global Smoke
Global Fire
Global FireW
Global Sparks
Global zeml

Global boom
Global texboom
Global boom1
Global texboom1
Global booma
Global texbooma

Global SparksB
Global Spark

createworld()
Global Gdir$=CurrentDir()
Global Damage
Global DamageD
Global DamageNo

Global parent
Global mdf
;==============debugger
InitDebugger()
ClearLog ( )
;==============
.men
EntityParent cam,0
RotateEntity cam,0,0,0

 ;------------Test---------------


;=====================menu
CreateMenu()
;---------------------------
  If game=0  LoadMenLevel()
  ;If gamel=1 gamel=0

acc$=CommandLine$()

If acc$="/nomenu" 
  mencfg=0
End If


If mencfg=1

   Menu()
Else
  timeTur=timeTurM

EndIf

StopChannel chanM
FreeMenu()
If game=20 game=0:EndGame():Goto nac
If game=10 game=0:EndGame():Goto nac

If game<>0 
   cam()
   Goto game
EndIf
;=====================
CreateInter()
audSa=audS/100



  fps=50*fpsSpeed
  period = 1000 / FPS
;===========================
Collisions 4,4,1,2    ;���� - ����
Collisions 4,5,2,2    ;���� - �����������
Collisions 4,1,2,2    ;���� - �����
Collisions 3,2,2,1    ;������-�������
Collisions 3,4,1,1    ;������ - ����
Collisions 3,1,2,1    ;������ - �����
Collisions 3,5,2,1    ;������ - �����������
Collisions 10,2,2,2   ;camera - terrein
Collisions 4,6,1,2    ;���� - bonus
;===============================================================================
;������������� ������
;===============================================================================

;=================
CreateScene(Level$)

angar()

LoadRobot()

If command=0
For t.tank=Each tank
shet(t.tank)
Next
Else
 shetCom()
EndIf

InitTankScripts ( ) 


;---------------------------------camera


PositionEntity cam,EntityX(centr),EntityY(centr),EntityZ(centr)
PositionEntity campiv,EntityX(centr),EntityY(centr),EntityZ(centr)
EntityParent cam,campiv
MoveEntity campiv,0,100,0




If upr=1
             ShowEntity pric1
             ShowEntity pric2
             ShowEntity krest
             ShowEntity Pcel
           
      Else
            HideEntity pric1
            HideEntity pric2
           HideEntity krest
           HideEntity Pcel
      EndIf
;--------------------------
If player=1
upr=1
      NextCamP()
      ShowEntity pric1
      ShowEntity pric2
      ShowEntity krest
      ShowEntity Pcel
     ; pop#=8
EndIf
;===============================================================================
;MAIN LOOP START
radar(0)
;===============================================================================
;SetFont fon1
Color 255,200,0
tmm#=MilliSecs()


;--------------------------------------------------------------------------------
While Not KeyHit(1)
  ;-------------
  ;�������������
	Repeat
		elapsed = MilliSecs () - Time
	Until elapsed	
	ticks = elapsed / period	
	tween# = Float (elapsed Mod period) / Float (period)	
For framelimit = 1 To ticks
	If framelimit = ticks CaptureWorld 
	Time = Time + period
   UpdateWorld

    ;---------------------------------------------------------------------------
If KeyHit(19) 
    rad=1-rad
    radar(rad)
EndIf



	MHIT=MouseHit(1)
	If KeyHit(28)
	    If player=0
	    upr=1-upr
	    
	If mdf<>1
	  cam()
       mdf=1
        If parent=0 firstPar()
    EndIf

      If upr=1
             ShowEntity pric1
             ShowEntity pric2
             ShowEntity krest
             ShowEntity Pcel
        ; pop#=8
      Else
            HideEntity pric1
            HideEntity pric2
           HideEntity krest
           HideEntity Pcel
        ;pop#=8
      EndIf
      EndIf
	EndIf
    ;�������� 
        mxs# = MouseXSpeed()/pop
		mys# = MouseYSpeed()/(pop*2)
		
		dest_xang# = dest_xang + mys
		dest_yang# = dest_yang - mxs
	
		xang# = CurveValue (xang, dest_xang, 5)
		yang# = CurveValue (yang, dest_yang, 5)

   If upr=0
	   If xang>89 xang=89:dest_xang=89
	   If xang<-89 xang=-89:dest_xang=-89 
   Else
        If xang>17 xang=17:dest_xang=17
	     If xang<-10 xang=-10:dest_xang=-10 
   EndIf      
       MoveMouse xcur,ycur

If mdf=0 Or upr=1
		If KeyDown (200)Or KeyDown(up_k)
			zc#=steps  		
		EndIf
	
		If KeyDown (208)Or KeyDown(down_k)
			zc#=-steps
		EndIf
		If KeyDown (203)Or KeyDown(left_k)
			xc#=-steps
		EndIf

		If KeyDown (205)Or KeyDown(right_k)
			xc#=steps
		EndIf
If upr=0
		
If EntityY(campiv)>950 PositionEntity campiv,EntityX(campiv),950,EntityZ(campiv)
If EntityX(campiv)>2500 PositionEntity campiv,2500,EntityY(campiv),EntityZ(campiv)
If EntityX(campiv)<-2500 PositionEntity campiv,-2500,EntityY(campiv),EntityZ(campiv)
If EntityZ(campiv)>2500 PositionEntity campiv,EntityX(campiv),EntityY(campiv),2500
If EntityZ(campiv)<-2500 PositionEntity campiv,EntityX(campiv),EntityY(campiv),-2500

MoveEntity campiv,xc, 0,zc
zc=0
xc=0
Else
   moveT=zc
   angleT=xc

zc=zc/1.5
xc=xc/1.5
zc=0
xc=0
EndIf
;--------------------------------------				
EndIf
	        


;--------------------FUNCTION--------------------------------
UpdateAnim()
UpdateFire()
UpdateOsk()
UpdateFlash()
updateSprite()

UpdateBonus()
;--------------------------------------------
If KeyHit(15)
   chall=1-chall
 If  chall=1
     showAll()
 Else
    hideAll()
 EndIf
EndIf
If KeyHit(50)
   mscr=1-mscr
EndIf
;--------------------------------------------------------------	

;If MHIT And mdf=0
; Picked=CameraPick(cam, mox, moy)
; If Picked<>0
    ;bum2(Picked)
; EndIf
;EndIf


If MHIT And mdf<>0 And upr=0
   detP=0
  cam()
  NextPar()
EndIf

If MouseHit(2)
  detP=0
 If player=0 And upr=0
    cam()
   NextCam()
  EndIf
EndIf

;--------rotate camera-------------- 
Select mdf
Case 0 
 RotateEntity campiv,xang,yang,0 
Case 1
    
If upr=0 
  If detP=0
   cam()
   PositionEntity campiv,EntityX(parent),EntityY(parent),EntityZ(parent)
  RotateEntity cam,0,EntityYaw(cam),0
  ;RotateEntity campiv,EntityPitch(campiv),EntityYaw(parent),0 
   RotateEntity campiv,0,EntityY(parent),0
    TranslateEntity cam,0,50,0
    MoveEntity cam,0,0,-90 
 


  RotateEntity campiv,0,yang,0 
   RotateEntity cam,xang,EntityYaw(cam),0
  EndIf
Else
   cam()
   PositionEntity campiv,EntityX(parent),EntityY(parent),EntityZ(parent)
  RotateEntity cam,0,EntityYaw(cam),0
  RotateEntity campiv,EntityPitch(campiv),EntityYaw(parent),0
   TranslateEntity cam,0,60,0
  MoveEntity cam,0,0,-90 

  RotateEntity campiv,0,yang,0
  RotateEntity cam,xang,EntityYaw(cam),0
  EntityPickMode parent,0 
  ent=CameraPick(cam,xcur,ycur)
  EntityPickMode parent,1,0

   If ent<>0
     PositionEntity pivK,PickedX(),PickedY(),PickedZ()
   EndIf
    If MouseDown(1) fireK=1
      If MouseDown(2) fireR=1
EndIf 
 
Case 2
 If detP=0
   cam()
  PositionEntity campiv,EntityX(parent),EntityY(parent),EntityZ(parent)
  RotateEntity cam,0,EntityYaw(cam),0
  RotateEntity campiv,EntityPitch(campiv),EntityYaw(parent),0   
  TranslateEntity cam,0,100,0
  MoveEntity cam,0,0,-180
RotateEntity campiv,0,yang,0 
   RotateEntity cam,xang,EntityYaw(cam),0
 EndIf
End Select

   PositionEntity dome,EntityX(cam,1),EntityY(cam,1),EntityZ(cam,1)
UpdateWorlds()

If tmm#+1000<=MilliSecs()
tmm#=MilliSecs()
timeTur=timeTur-1*fpsSpeed


;-------------------end tur---------------------
If timeTur<=0
    Log()
   showAll()
     For t.tank=Each tank
       HideEntity t\inf
     Next 
endtab=LoadMesh("menu\tab.3DS")
ScaleMesh endtab,.005,.006,.01
tg=LoadMesh("menu\tab.3DS")
ScaleMesh tg,.005,.006,.01
PositionMesh tg,0,-.18,0
AddMesh tg,endtab
FreeEntity tg

EntityColor endtab,0,0,0
EntityAlpha endtab,.6
PositionEntity endtab,EntityX(cam,1),EntityY(cam,1),EntityZ(cam,1)
RotateEntity endtab,EntityPitch(cam,1),EntityYaw(cam,1),EntityRoll(cam,1)
MoveEntity endtab,0,.8,2


RenderWorld
SetFont fon4
Color 50,50,50
yy#=85
xx#=450
Text xx*fx,yy*fx,"Win"
Text xx+50*fx,yy*fx, winN$
Text xx+2*fx,yy*fx,"Win"
Text xx+52*fx,yy*fx, winN$
Text xx*fx,yy+2*fx,"Win"
Text xx+50*fx,yy+2*fx, winN$
Text xx+2*fx,yy+2*fx,"Win"
Text xx+52*fx,yy+2*fx, winN$
Color 200,200,200
Text xx+1*fx,yy+1*fx,"Win"
Text xx+51*fx,yy+1*fx, winN$
yy=yy+20
Color 50,50,50
Text 450*fx,yy+43*fx,"Press Enter"
Text 452*fx,yy+45*fx,"Press Enter"
Text 452*fx,yy+43*fx,"Press Enter"
Text 450*fx,yy+45*fx,"Press Enter"
Color 200,200,200
Text 451*fx,yy+44*fx,"Press Enter"





   Flip
   .nog
   WaitKey
   If KeyHit(28)
   endgame()
    game=0
     If mencfg=1
        Goto nac
     Else
       End
     EndIf
   Else
     Goto nog
   EndIf
EndIf
EndIf
;[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[
Next

If fpsSpeed<=3 
   RenderWorld(tween)
Else
 Cls 
EndIf

If rad=1
  ri#=ri-.043
 If ri<-1 ri=0
 PositionTexture textPad,ri,ri
EndIf

    frames=frames+1
	If MilliSecs()-render_time=>1000  Then afps=frames : frames=0 : render_time=MilliSecs()
	Color 255,255,255
	 SetFont fon4
;Text 900*fx,20*fx,"FPS   "+afps

;Text 500*fx,90*fx,TotalVidMem()
;Text 500*fx,60*fx,Repair
; enemy=0
;Repair=0
;Rocketb=0
;Bullet=0
;yyy=100
;For t.tank=Each tank
  ;Text 10,yyy,t\live
   ;yyy=yyy+20
;Next
;=================
If mscr=1
   Text 800*fx,30*fx,"Time limit   "+timelimit
EndIf
If mencfg=0 And upr=0
   If mes>0
     updatemessag()
   EndIf
   Color 200,200,200
  Text 50*fx,hei-38*fx,"Press key: D - vars ai   F - vars user                                     M - time limit    R - show\hide radar"
EndIf

;--------------timer----------------
sec=timeTur Mod 60
min=(timeTur-sec)/60
edi=sec Mod 10
des=(sec-edi)/10
xe#=430
DrawImage akka(min),xe*fx,hei-43*fx

Color 255,255,255
Oval (xe+35)*fx,hei-35*fx,6*fx,6*fx
Oval (xe+35)*fx,hei-23*fx,6*fx,6*fx

DrawImage akka(des),(xe+50)*fx,hei-43*fx
DrawImage akka(edi),(xe+85)*fx,hei-43*fx
If upr=1
;--------bullet------
  edi=UprBullet Mod 10
  des=(UprBullet-edi)/10
xe=900
DrawImage akkat(des),(xe)*fx,hei-40*fx
DrawImage akkat(edi),(xe+25)*fx,hei-40*fx
If UprRocket>0 DrawImage imRoc,(xe+60)*fx,hei-40*fx
;------lives-------
edi=UprLive Mod 10
des=(UprLive-edi)/10
xe=50
DrawImage imliv,(xe-35)*fx,hei-40*fx
DrawImage akkat(des),(xe)*fx,hei-40*fx
DrawImage akkat(edi),(xe+25)*fx,hei-40*fx

EndIf
;=================
deb=GetKey()
If deb=100
  If deba=0 
     deba=1
  Else
     deba=0
  EndIf
  deb=0
EndIf
If deba=1   DrawDebuggerA(550*fx,0)
If deb=102
  If debb=0 
     debb=1
  Else
     debb=0
  EndIf
  deb=0
EndIf
If debb=1   DrawDebuggerB(850*fx,0)
;==================================
;yyy=100
;For a.angar=Each angar
;yyy=yyy+20
; Text 100,yyy,a\kol
;Next





.game
If KeyHit(88) skrinshot()
Flip 0
Wend
HideEntity spriteAll  
HideEntity tabAll
HideEntity pric1
HideEntity pric2
HideEntity krest
chall=0
game=2
;endgame()
If mencfg=1 Goto men
End
;0000000000000000000000000000000000000000000000000000000000000000000000000
;===========================