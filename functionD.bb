Function EndGame()
ClearWorld
EndGraphics 
DeleteAllScripts()
Delete Each tank
Delete Each objekt
Delete Each fire
Delete Each osk
Delete Each Flash
Delete Each part
Delete Each ter
Delete Each knop
Delete Each lev
Delete Each rem
Delete Each robot
Delete Each anim
Delete Each fisik
Delete Each bonus


Delete Each  Shadow_Caster
Delete Each  Shadow_Receiver
Delete Each  Light_Caster
Delete Each  Shadow
Delete Each  Caster_Set
Delete Each  Static_Shadow
Delete Each  Static_Shadow_Texture
Delete Each  Static_Shadow_Caster


;-------------------
;Delete Each script
mdf=0
campiv=0
parent=0
upr=0
winN$=""
win=0
Redpoint=0
Bluepoint=0


FlushMouse
EndGraphics
End Function
;====================
Function createTank$(n$,File$)

File$="scripts\"+File$
t.tank=New tank
mmm$=initScript$(t, File$)
If mmm$="faled"
          Delete t
          
	   Return mmm$
Else
	
	t\m$=mmm$
	
	t\File$=File$
	t\name$=NameBot$
	AddToLog ( "            name="+t\name)
	AddToLog ( "            model="+t\m)
	t\name$=name$(t\name$)
	t\Color$="red"
	Return t\name$
 EndIf	
End Function
;===================
Function createPlayer()

	c=0
	For t.tank = Each tank
		c=c+1
	Next
	t.tank=New tank
	If ModPlayer$=""
	 r.robot=First robot
	 ModPlayer$=r\name$
	EndIf
	t\m$=ModPlayer$
	t\name$=namePl$
	t\Color$="red"
End Function
;==========================
Function LoadRobot()
	kol=1
	For t.tank=Each tank
		AddToLog ( "LoadRobot "+t\m )
		t\kol=kol
		kol=kol+1
		t\live=maxlive
		
		inf(t.tank)
		MoveEntity t\inf,0,20,0
		ScaleSprite t\inf,20,20
       NameEntity t\inf,"inf"

t\pivot1=CreatePivot() 
t\pivot2=CreatePivot()
EntityRadius t\pivot2,20        
t\pivotE=CreateCube()
FitMesh t\pivote,-MeshWidth(t\pivote)/2,-MeshHeight(t\pivote)/2,.01,MeshWidth(t\pivote),MeshHeight(t\pivote),MeshDepth(t\pivote)
If MeshDepth(t\pivote)<>1500
  st#=(1500/MeshDepth(t\pivote))
  ScaleMesh t\pivote,.3,.3,st
   EntityColor t\pivote,0,255,0
   EntityFX  t\pivote,1
   EntityOrder  t\pivote,-2
EndIf            

          t\pivot=LoadMesh("menu\radar.3ds")
       ;RotateMesh t\pivot,-90,90,90
       ;FitMesh t\pivot,-MeshWidth(t\pivot)/2,-MeshHeight(t\pivot)/2,0,MeshWidth(t\pivot),MeshHeight(t\pivot),MeshDepth(t\pivot)
            
             If MeshDepth(t\pivot)<>1500
                  st#=(1500/MeshDepth(t\pivot))
                 ScaleMesh t\pivot,st,st,st
             EndIf
           EntityTexture t\pivot,textPad
           EntityColor t\pivot,0,0,150
            EntityBlend t\pivot,3
            EntityOrder t\pivot,-1
                 EntityFX t\pivot,8
            ;TranslateEntity t\pivot,0,20,0

		t\corpus=LoadAnimMesh("Bots\"+t\m$+".b3d")

		If command=1
		   patch$="Bots\"+Left(t\m$,3)
		  If t\Color$="red"  patch$=patch$+"red.jpg"
		  If t\Color$="blue" patch$=patch$+"blu.jpg"
		   texture=LoadTexture(patch$)
		  Texturemesh (t\corpus,texture,0)
		EndIf 
		t\wh1=FindChild(t\corpus,"wh1")
		t\wh2=FindChild(t\corpus,"wh2")
		t\wh3=FindChild(t\corpus,"wh3")
		t\wh4=FindChild(t\corpus,"wh4")
		
		t\pivWh1=CreatePivot()
		PositionEntity t\pivWh1,EntityX(t\wh1,1),EntityY(t\wh1,1),EntityZ(t\wh1,1)
		EntityParent t\wh1,t\pivWh1
		EntityParent t\pivWh1,t\corpus
		
		t\pivWh2=CreatePivot()
		PositionEntity t\pivWh2,EntityX(t\wh2,1),EntityY(t\wh2,1),EntityZ(t\wh2,1)
		EntityParent t\wh2,t\pivWh2
		EntityParent t\pivWh2,t\corpus
           NameEntity t\pivWh1,"inf"
		NameEntity t\pivWh2,"inf"
		
		
		shad=LoadMesh("Bots\"+t\m$+".b3d")
		t\turret=FindChild(t\corpus,"Turret")
		t\stvol=FindChild(t\corpus,"Gun")
		
		mw#=MeshDepth(shad)
		
		;Stop
		sc#=55/mw

       ScaleEntity t\corpus,sc,sc,sc
           FreeEntity shad
           		;------shadows---------
                     t\sh=LoadSprite("menu\shad.png",2)
                     EntityColor t\sh,0,0,0
                     SpriteViewMode t\sh,2
                     RotateEntity t\sh,90,0,0
                     ;EntityAlpha t\sh,.7
                     ;EntityParent t\sh,t\corpus
                      ScaleSprite t\sh,14,24
                     ; TranslateEntity t\sh,0,.5,0
                     ;-------------------------
		t\height=4
		NameEntity t\corpus,t\Color$

		EntityRadius t\corpus,30
		EntityPickMode t\corpus,1,0
		;t\tt=texture(t\turret)

		EntityParent t\inf,t\turret

		t\vihlop=LoadSprite ("Sprites\www8.png")
		EntityFX t\vihlop,17
		ScaleSprite t\vihlop,1,15
		SpriteViewMode t\vihlop,2
		
		PositionEntity t\vihlop,EntityX(t\stvol,1),EntityY(t\stvol,1),EntityZ(t\stvol,1)+30
		TurnEntity t\vihlop,90,0,0
		EntityParent t\vihlop,t\stvol
		EntityAlpha t\vihlop,0
		NameEntity t\vihlop,"inf"
		
   ;t\xres=t\xres+1
  ;If t\xres=5 t\xres=1
   For a.angar=Each angar
    If a\kol=t\xres
      PositionEntity t\corpus,a\x,a\y-20,a\z
         t\yc=a\y-20
    EndIf
   Next

         ;Stop
		
		t\ang=200
		EntityParent t\stvol, t\turret
		
		PointEntity t\corpus,centr
		EntityType t\corpus,4
		
		t\rocket=maxRocket
		t\bullet=maxBullet
		
		
		;===================
	Next
End Function
;=======================
Function skrinshot()

aa=1
fold$=CurrentDir$()+"\screens"
dir=ReadDir(fold$)
Repeat
p$="screen"+aa+".bmp"
File$=NextFile$(dir)
If File$="" Then Exit
If FileType(fold$+"\"+File$)=1
  If p$=File$ aa=aa+1
EndIf
Forever
CloseDir dir
pp$="screens\"+"screen"+aa+".bmp"

SaveBuffer (BackBuffer(),pp$)
End Function
;======================

Function CurveValue#(current#,destination#,curve)
	current#=current#+((destination#-current#)/curve)
	Return current#
End Function
;-----------------------------------------
Function createworld()


;kkt=CreateCube()
;FlipMesh kkt
;ScaleEntity kkt,2000,2000,2000
;EntityPickMode kkt,2
;HideEntity kkt
;----------------------
pivK=CreatePivot()
;pivK=CreateSphere()
;ScaleEntity pivK,10,10,10

Pcel=CreateSprite()
texc=CreateTexture(32,32)
EntityTexture Pcel,texc
Color 255,0,0
SetBuffer TextureBuffer(texc)
  ClsColor 0,0,0
 Cls
 Oval 5,5,30,30
SetBuffer BackBuffer()
EntityBlend Pcel,3
EntityFX Pcel,1
EntityOrder Pcel,-1
HideEntity Pcel
;-------------------------sound

sgus=LoadSound ("sound\motor.wav")
swal=LoadSound ("sound\wall.WAV")
sgun=LoadSound ("sound\1.wav")
sbah=LoadSound ("sound\3.wav")
sbah3=LoadSound ("sound\4.wav")
sBon=LoadSound ("sound\hit.wav")
sRoc=LoadSound ("sound\ROCET.WAV")
;---------------------------
Damage=LoadTexture("texture\TexDamage2.jpg")
DamageD=LoadTexture("texture\Burn.png")
ScaleTexture DamageD,4,4
DamageNo=CreateTexture(1,1)
SetBuffer TextureBuffer(DamageNo)
  ClsColor 255,255,255
  Cls
  ClsColor 0,0,0
SetBuffer BackBuffer()
;----------------------------------
Smoke=LoadSprite("Sprites\floor.PNG",2)                 
PositionEntity  Smoke,0,-10000,0
EntityBlend  Smoke,1
EntityFX  Smoke,9
HideEntity Smoke
;----------------------------------
zeml=LoadSprite("Sprites\blood.png",2)                 
PositionEntity  zeml,0,-10000,0
EntityBlend  zeml,1
EntityFX  zeml,9
HideEntity zeml
;------------------------------------
Spark=LoadSprite ("Sprites\plasm.png")
;EntityBlend  Spark,3
PositionEntity Spark,0,-10000,-3.6
EntityFX Spark,9
HideEntity Spark
;----------------------------------
Fire=LoadSprite ("Sprites\8.png")
PositionEntity Fire,0,-10000,-3.6
EntityFX Fire,9
HideEntity Fire
;------------------------------------
FireW=LoadSprite ("Sprites\fair.jpg")
PositionEntity FireW,0,-10000,-3.6
EntityFX FireW,9
HideEntity FireW
;----------------------------------------------------
Sparks=LoadSprite ("Sprites\Sun.png")
PositionEntity Sparks,0,-10000,-3.6
EntityFX Sparks,9
HideEntity Sparks
;-------------------------------
boom=CreateSprite ()
texboom=LoadAnimTexture("Sprites\boom.tga",4,128,128,0,15)
EntityTexture boom,texboom,0
PositionEntity boom,0,-10000,-3.6
EntityBlend  boom,3
EntityFX boom,9
HideEntity boom
;---------------------------------
booma=CreateSprite ()
texbooma=LoadAnimTexture("Sprites\boom1.tga",4,128,128,0,15)
EntityTexture booma,texbooma,0
PositionEntity booma,0,-10000,-3.6
EntityBlend  booma,3
EntityFX booma,9
HideEntity booma
;---------------------------------
boom1=CreateSprite ()
texboom1=LoadAnimTexture("Sprites\b.png",3,128,128,0,29)
EntityTexture boom1,texboom1,0
PositionEntity boom1,0,-10000,-3.6
EntityBlend  boom1,1
EntityFX boom1,9
HideEntity boom1
;-------------------------------------------
SparksB=LoadSprite ("Sprites\fair.jpg")
PositionEntity Sparksb,0,-10000,-3.6
EntityFX Sparksb,9
HideEntity SparksB
;---------------------------------
osk=LoadSprite("Sprites\stA1.PNG",4)                 
PositionEntity  osk,0,-10000,0
HideEntity  osk
EntityFX  osk,9
HideEntity osk
;---------------------------------
oskT1=LoadMesh("Sprites\1.3DS")
ScaleMesh oskT1,.015,.015,.015
ss=Rand(0,70)
EntityColor oskT1,ss,ss,ss
HideEntity oskT1
;---------------------------------
oskT2=LoadMesh("Sprites\2.3DS")
ScaleMesh oskT2,.015,.015,.015
ss=Rand(0,70)
EntityColor oskT2,ss,ss,ss
HideEntity oskT2
;---------------------------------
oskT3=LoadMesh("Sprites\3.3DS")
ScaleMesh oskT3,.015,.015,.015
ss=Rand(0,70)
EntityColor oskT3,ss,ss,ss
HideEntity oskT3
;---------------------------------
oskT4=LoadMesh("Sprites\4.3DS")
FitMesh oskT4,-MeshWidth(oskT4)/2,-MeshHeight(oskT4)/2,-MeshDepth(oskT4)/2,MeshWidth(oskT4),MeshHeight(oskT4),MeshDepth(oskT4)
ScaleMesh oskT4,7,7,7
ss=Rand(0,70)
EntityColor oskT4,ss,ss,ss
HideEntity oskT4
;-----------------------------
bulletM=LoadMesh("menu\bullet.b3d")
ScaleEntity bulletM,.03,.03,.03
EntityType bulletM,6
EntityRadius bulletM,20
EntityPickMode bulletM,1
HideEntity bulletM
;-------------------------
rocketM=LoadMesh("menu\rocket.b3d")
ScaleEntity rocketM,.03,.03,.03
EntityType rocketM,6
EntityRadius rocketM,20
EntityPickMode rocketM,1
HideEntity rocketM
;-------------------------
remontM=LoadMesh("menu\rem.b3d")
ScaleEntity remontM,.03,.03,.03
EntityType remontM,6
EntityRadius remontM,20
EntityPickMode remontM,1
HideEntity remontM
;------------------------------
rocket=LoadMesh("menu\rocket.3ds")
EntityColor rocket,20,20,50
EntityShininess rocket,1
ScaleEntity rocket,.01,.01,.01
EntityType rocket,3
EntityRadius rocket,1
HideEntity rocket
End Function
;==========================
Function CreateScene(Level$)


TexS=LoadTexture("texture\M25.JPG")

texK=LoadBrush("texture\M25.JPG",64)
put$="level\"+level$+"\"+Right(level$,2)+".scn"

wf=ReadFile(put$)
If wf<>0

kolic= ReadInt(wf)                    
xd#=ReadFloat(wf)                 
yd#=ReadFloat(wf)       
zd#=ReadFloat(wf)

xe#=ReadFloat(wf)                   
ye#=ReadFloat(wf)        
ze#=ReadFloat(wf)

skybox=ReadInt(wf)
;=====================DOME============
dome=MakeSkyBox( "level\"+level$+"\skybox\sky")
EntityFX dome,9
EntityOrder dome,10
EntityPickMode dome,2,1
ScaleEntity dome,100,100,100
;EntityColor dome,150,150,150
;---------------------------------------------------------------------------;ambient
ram=ReadFloat(wf)
gam=ReadFloat(wf)    
bam=ReadFloat(wf)
AmbientLight -ram*2.5,-gam*2.5,-bam*2.5
;--------------------------------------------------------;fog
tfog=ReadInt(wf)
ot=ReadFloat(wf)  
do=ReadFloat(wf)        
raf=ReadFloat(wf)
gaf=ReadFloat(wf)    
baf=ReadFloat(wf)

If tfog=1
   ;CameraFogMode cam,1
  ;CameraFogRange cam,-ot*30,-do*30
  ;CameraFogColor cam, -raf*2.5,-gaf*2.5,-baf*2.5
  ;CameraClsColor cam, -raf*2.5,-gaf*2.5,-baf*2.5
EndIf
;----------------------------------------------------------------------
For i=1 To kolic
o.objekt=New objekt

o\sc=ReadFloat(wf)
o\x#=ReadFloat(wf)
o\y#=ReadFloat(wf)
o\z#=ReadFloat(wf)
	
o\xr#=ReadFloat(wf)
o\yr#=ReadFloat(wf)
o\zr#=ReadFloat(wf)
	
o\name$=ReadString(wf)
o\kind$=ReadString(wf)
o\types$=ReadString(wf)
o\destroy$=ReadString(wf)
o\trigger$=ReadString(wf)
o\trigger1$=ReadString(wf)
o\trigger2$=ReadString(wf)
o\trigger3$=ReadString(wf)
o\action1$=ReadString(wf)
o\action2$=ReadString(wf)
o\action3$=ReadString(wf)
o\atrigger$=ReadString(wf)
o\ttime$=ReadString(wf)
o\patch$=ReadString(wf)
o\child$=ReadString(wf)

o\r=ReadFloat(wf)
o\g=ReadFloat(wf)
o\b=ReadFloat(wf)
o\parent$=ReadString(wf)
o\skor$=ReadString(wf)


If 	o\kind$="triggers"
o\xtr#=ReadFloat(wf)
o\ztr#=ReadFloat(wf)
EndIf

If o\kind$="light"
	o\ran=ReadFloat(wf)
EndIf
;----------------------------------------------------------------------------
  Next
CloseFile(wf)
  Else
;SetFont fon
 Color 255,255,255
 	Text 450*fx,300*fx,"Level End"
   Color 255,0,0
 	Text 452*fx,301*fx,"Level End"
game=10
Flip
WaitKey

EndIf


;++++++++++++++++++++++++++++++++++++++++create scene
 kol=0
For o.objekt=Each objekt
If o<>Null
;===============================================================BILDING
If o\kind$="building" 

                               ;bilding 
;------------------------------------      
     
      o\en=LoadMesh(o\patch$)
      FitMesh o\en,-MeshWidth(o\en)/2,0,-MeshDepth(o\en)/2,MeshWidth(o\en),MeshHeight(o\en),MeshDepth(o\en)
        
 ;--------------------------------position
             PositionEntity o\en,o\x,o\y,o\z
             RotateEntity o\en,o\xr,o\yr,o\zr
             ScaleMesh o\en,o\sc,o\sc,o\sc
 
         
If Left(o\name$,2)="an"
  kol=kol+1
  a.angar = New angar
 
  a\kol=kol
  a\x=o\x
  a\y=o\y+60
  a\z=o\z
EndIf
         ;--------------------------------------------------------
          If o\types$="terrain" Or o\types$="water" Or Left(o\name$,2)="an"            ;terrain
            EntityType o\en,Env
            EntityPickMode o\en,2,1
            NameEntity o\en,"terrain"
           ; ScaleEntity o\en,1,.4,1 
                EntityType o\en,2

         Else
                                    
                 
                                
            If Left(o\name$,3)="wal"
               NameEntity o\en,"wall" 
               EntityType o\en,1 
               EntityPickMode o\en,2,1   
            Else
                If effect=3  Cast_Static_Shadow(o\en)   ;SHADOW 
                NameEntity o\en,"building"
               EntityType o\en,5 
               EntityPickMode o\en,2,1
            EndIf 
                
        EndIf      
        ;----------------------------------terrain
If o\types$="terrain"
   terrain=o\en 
 If effect=3  Receive_Shadow(o\en)              ;SHADOW  

          
If Left(o\name$,3)<>"noc" 
shadK=1
If effect=3  Receive_Shadow(o\en)              ;SHADOW 

Ctexa=CreateTexture(texter,texter)
EntityTexture terrain ,ctexa,0,3
SetBuffer TextureBuffer( Ctexa )
ClsColor 255,255,255 
Cls
SetBuffer BackBuffer()
ClsColor 0,0,0 

wt#=MeshWidth(o\en)
dt#=MeshDepth(o\en)

ta.ter=New ter
ta\en=terrain
ta\Tex=ctexa
cctex=ta\Tex
ta\sizew=TextureWidth (Ctexa)
ta\sizeH=TextureHeight (Ctexa)
namt$=Left(o\name$,2)

EndIf

NameEntity terrain,"terrain"
;EntityOrder terrain,2

EndIf
;=======================================================================end bilding
EndIf


If Left(o\name$,5)="centr"
  centr=CreatePivot()
  PositionEntity centr,o\x,o\y,o\z
EndIf
;============================================================LIGHT
If o\kind$="light"
    If o\types$ ="global" o\en=CreateLight(1)
    If o\types$ ="point" o\en=CreateLight(2)   
    If o\types$ ="spot" o\en=CreateLight(3)
   LightColor o\en,-o\r*2.5,-o\g*2.5,-o\b*2.5
   LightRange o\en,-o\ran*30
   LightConeAngles o\en,0,60
   PositionEntity o\en,o\x,o\y,o\z
RotateEntity o\en,o\xr,o\yr,o\zr

 If effect=3 And shad=0 
       cast_light(o\en,100000):shad=1                          ;SHADOW 
       shadL=CreatePivot()
       PositionEntity shadL,o\x,o\y,o\z
  EndIf

;=============================================================end LIGHT
;---------------------------------------------

EndIf
;----------------------------
EndIf
  If Left(o\name$,1)="b"
      Createbonus(Left(o\name$,2),o\x#,o\y#+20,o\z#)
   EndIf
Next

 If effect=3 
    Update_Static_Shadows(Cam)

  EndIf
End Function
;==================
Function Createbonus(nam$,x#,y#,z#)
;---------------------bullet--------------

b.bonus=New bonus
Select nam$
Case "bb"
  b\tip=1
  b\en=CopyEntity (bulletM)
  NameEntity b\en,"bul"
Case "br"
  b\tip=2
  b\en=CopyEntity (rocketM)
  NameEntity b\en,"roc"
Case "bl"
   b\tip=3
   b\en=CopyEntity (remontM)
  NameEntity b\en,"rem"
End Select




  PositionEntity b\en,x,y,z


End Function
;=========================
Function angar()
kol=0
 For t.tank=Each tank
   kol=kol+1
     If kol>4  kol=1
   For a.angar=Each angar
    If a\kol=kol
       t\xres=a\kol
    EndIf
   Next
 Next 
End Function
;-==================================
Function UpdateWorlds()

For t.tank=Each tank

;--------------info---------------------
  di#=EntityDistance(t\corpus,cam)
  sc#=di/30+5
  ScaleSprite t\inf,sc,sc
;---------------sound--------------
If t\soundP<55
  t\soundP=t\soundP+Abs(t\move)*5
EndIf

ChannelPitch t\sound,400*(t\soundP+40)
;otladka=t\soundP
t\soundP=t\soundP/1.1
;-----------------------------------

 If ChannelPlaying ( t\sound)=0
     t\sound=PlaySound (sgus)
 EndIf
  vol#=audSa / (EntityDistance (t\corpus,cam)/100)
  If t\corpus=parent vol=vol-.3
 ChannelVolume t\sound,vol 
If t\speed>0 t\speed=t\speed-1
If t\speed2>0 t\speed2=t\speed2-1

If t\al>0
  t\al=t\al-.2
  kl(t\stvol,40-t\al*40 )
 EntityAlpha t\vihlop,t\al
EndIf

MoveEntity t\corpus,0,0,t\z
If t\live>0 rotateAtPosition(t.tank)

;--------------coordinates stvol-------------
t\xs=EntityX(t\stvol,1)
t\ys=EntityY(t\stvol,1)
t\zs=EntityZ(t\stvol,1)

t\rxs=EntityPitch(t\stvol,1)
t\rys=EntityYaw(t\stvol,1)
t\rzs=EntityRoll(t\stvol,1)
;-----------------------rotate stvol---------------
If t\rxs-t\rxt>0 RotateEntity t\stvol,t\rxt,t\ryt,t\rzt,True
If t\rxs-t\rxt<-40 RotateEntity t\stvol,t\rxt-40,t\ryt,t\rzt,True
;--------gravity-------------
If t\live>0 gravity(t.tank)
;------------------------------------------------turn tanks-----------------
If t\angl>1 t\angl=1
If t\angl<-1 t\angl=-1

t\anglc=t\angl*Abs(max/t\z)

If t\anglc>maxC t\anglc=maxC
If t\anglc<-maxC t\anglc=-maxC

 TurnEntity t\corpus,0,t\anglc*(t\z/4)+t\anglD,0

;---------angle tank------------------------
t\rxc=EntityPitch(t\corpus)
t\ryc=EntityYaw(t\corpus)
t\rzc=EntityRoll(t\corpus)
;--------------Move tanks------------------

If t\move>1 t\move=1
If t\move<-1 t\move=-1
t\z=t\z+t\move*usk

poprt#=Abs(Cos(t\anglc*3))
t\z=t\z*poprt

If t\z>max 
  t\z=max
Else
    db#=-t\move*20
   rraz#=max-Abs(t\z)
   
  If Abs(t\move)>0
    If effect=3
      If  EntityInView ( t\corpus,cam ) 
         If EntityDistance(cam,t\corpus)<400  effectKoleso(t.tank) 
      EndIf  
    EndIf
     t\anglD=Sin(Time/(rraz*200))*rraz/4
  EndIf
EndIf
If t\z<-max 
  t\z=-max
Else
  db#=(t\z-max)*8*t\move
EndIf

If Abs(t\rxc)>20 t\z=t\z+t\rxc/400

t\anglc=t\anglc/1.01
t\z=t\z/1.01
t\move=t\move/1.05
t\anglD=t\anglD/1.1
;-----------------animate--------------
TurnEntity t\wh1,-t\z*8,0,0
TurnEntity t\wh2,-t\z*8,0,0

TurnEntity t\wh3,-t\z*8+db,0,0
TurnEntity t\wh4,-t\z*8+db,0,0


TurnEntity t\pivWh1,0,t\angl,0
If Abs(EntityYaw(t\pivWh1,0))>30 TurnEntity t\pivWh1,0,-t\angl,0

TurnEntity t\pivWh2,0,t\angl,0
If Abs(EntityYaw(t\pivWh2,0))>30 TurnEntity t\pivWh2,0,-t\angl,0

If Abs(t\angl)<.2
  TurnEntity t\pivWh1,0,-EntityYaw(t\pivWh1,0)/1.5,0
  TurnEntity t\pivWh2,0,-EntityYaw(t\pivWh2,0)/1.5,0
EndIf
;-------------collign-------------
t\collignP= EntityCollided(t\corpus,1)
t\collignW= EntityCollided(t\corpus,5)
t\collignt = EntityCollided(t\corpus,4)

;-----------------coordinates corpus------------
t\xc=EntityX(t\corpus)
t\yc=EntityY(t\corpus)
t\zc=EntityZ(t\corpus)


;-------------turn turret-----------
;yaw#=t\ryt-t\ryc
If t\angly>maxT t\angly=maxT
If t\angly<-maxT t\angly=-maxT

TurnEntity t\turret,0,t\angly,0
;---------coordinates turret---------
t\xt=EntityX(t\turret,1)
t\yt=EntityY(t\turret,1)
t\zt=EntityZ(t\turret,1)

t\rxt=EntityPitch(t\turret,1)
t\ryt=EntityYaw(t\turret,1)
t\rzt=EntityRoll(t\turret,1)
;-----------turn stvol--------------
If t\anglx>maxG t\anglx=maxG
If t\anglx<-maxG t\anglx=-maxG
TurnEntity t\stvol,t\anglx,0,0
;[[[[[[[[[[[[[[[[[[[[[[[[[[[[[
If t\live>0 
    If upr=0 Or parent<> t\corpus
    ;=======AI===========
 
    EntityPickMode terrain,0

PositionEntity t\pivot,t\xc,t\yc+15,t\zc
PositionEntity t\pivotE,t\xc,t\yc+15,t\zc
If rad=1
  radarEffect(t.tank)
EndIf
    If t\ang=0 
      UpdateScript(t.tank)
          ;================================
    Else
       t\live=maxlive
       t\ang=t\ang-1
       ClearValue (t\corpus)  
       If t\ang<100
          t\move=1 
        Else
           teleport(t.tank)
        EndIf
   EndIf
t\DamageYaw=0

       EntityPickMode terrain,2,1
       If parent= t\corpus yangM=t\ryt
   Else
      If parent= t\corpus   Upr(t.tank)
   EndIf
;=====fire=======
If t\bullet>0
  If t\fire<>0
      fire1(t.tank)
      t\fire=0
   EndIf
End If
;=====fire2=======
If t\rocket>0
  If t\fire2<>0
      fire2(t.tank)
      t\fire2=0
   EndIf
End If
;============

     t\v=t\v+(MilliSecs()-m)
   If t\live>0 
        ; PositionEntity t\corpus,t\xc,t\yc,t\zc
             ;RotateEntity t\stvol,t\rxs,t\rys,t\rzs,1
             ; RotateEntity t\turret,t\rxt,t\ryt,t\rzt,1
         RotateEntity t\corpus,t\rxc,t\ryc,t\rzc,1
             
            

    EndIf
;-----------------COLLIGN------------------------
   If t\collignP<>0 Or t\collignW<>0  Or t\collignT<>0
    If ChannelPlaying (swalC)=0
      swalC=PlaySound (swal)
      ChannelVolume swalC,(audSa / (EntityDistance (t\corpus,cam)/500))*(t\z/10)/8
    EndIf
     nx# =  CollisionNX (t\corpus, 1 )
      ny# = CollisionNY ( t\corpus, 1)
      nz# = CollisionNZ ( t\corpus, 1)
 

    AlignToVector t\corpus, nx,Abs(ny),nz,3,t\z/60
    PositionEntity t\pivot1,t\xc,t\yc,t\zc
   RotateEntity t\pivot1,t\rxc,t\ryc,t\rzc
   MoveEntity t\pivot1,0,0,10
     nn# = Sqr(nx*nx + ny*ny + nz*nz )
    vx# = EntityX(t\pivot1, 1) - EntityX(t\corpus)
    vy# = EntityY(t\pivot1, 1) - EntityY(t\corpus)
    vz# = EntityZ(t\pivot1, 1) - EntityZ(t\corpus)
    cosin# = (nx*vx + ny*vy + nz*vz)/nn/Sqr(vx*vx + vy*vy + vz*vz)
    ;otladka= cosin
     t\z=t\z-t\z*Abs( cosin/2)

     t\trx=(t\trx+nx*t\z*30)*cosin
     t\trz=(t\trz+nz*t\z*30)*cosin
      t\move=t\move/2
    EndIf

bonus=0
bonus=EntityCollided(t\corpus,6)
If  bonus<>0 
    ;Stop
   bonus(t.tank,bonus)
EndIf
;----------------------end collign------------
       TranslateEntity t\corpus,t\trx/8,0,t\trz/8
       t\trx=t\trx/1.1
       t\trz=t\trz/1.1
Else

t\angly=0
;=======death=========
UpdateFisik(t.tank)
If t\live>-100
    If parent=t\corpus UprLive=0

   If rad=1   
     HideEntity t\pivot
     HideEntity  t\pivote
   EndIf

   HideEntity t\sh
  t\death=t\death+1
    bum3a(t\corpus)
    fisik(t.tank)
    If parent=t\corpus detP=1
   HideEntity t\turret
  HideEntity t\stvol
  t\live=-100
   TextureMesh(t\corpus,DamageD,1)
Else
  ;--------------------------------------
 ; If t\live<-250 And t\live>-370 
      ;TranslateEntity t\corpus,0,-1,0
   ;Else
      
      
  ; EndIf
  ;-----------------------------------
  ClearValue (t\corpus)
  t\move=0
  t\live=t\live-1
  t\og=t\og+1
  If t\og>10
       sc#=10-(Abs(t\live+100)/60)
       If t\live>-270 dump(t\corpus,sc)
      t\og=0
       If t\live<-370 
       respawn(t.tank)
      EndIf
     EndIf

EndIf


EndIf

If t\yc<-600 respawn(t.tank)
If effect=3   shadows(t.tank)
Next

End Function
;=================
Function Upr(t.tank)
t\angl=0   
t\move=moveT
UprBullet=t\bullet
UprLive=t\live
UprRocket=t\rocket

t\angl=-angleT
     
    EntityParent t\turret,0
    t\anglY= DeltaYaw(t\turret,pivK)
    EntityParent t\turret, t\corpus

   EntityParent t\stvol,0
    t\anglX= DeltaPitch(t\stvol,pivK);-EntityDistance#(t\corpus,pivK)/500
    EntityParent t\stvol, t\turret
    t\fire=fireK
    fireK=0
      t\fire2=fireR
    fireR=0
angleT=0

moveT=0
;-----------------------------------------------------------
  If Abs(t\anglY)>2 Or Abs(t\anglx)>2
       If krestS<.008 
          krestS=krestS+.0004
          ScaleEntity krest,krestS,krestS,krestS
       Else
          RotateEntity pric1,0,0,EntityYaw(campiv)*5
          RotateEntity pric2,0,0,EntityPitch(cam)*10
        EndIf
   Else
         RotateEntity pric1,0,0,-EntityRoll(pric1)/40
         RotateEntity pric2,0,0,-EntityRoll(pric2)/40
        ; MoveEntity Pcel,0,0,dist1/5

  If krestS>.004
       krestS=krestS-.0004
       ScaleEntity krest,krestS,krestS,krestS
   EndIf

   EndIf
End Function
;================
Function fisik(t.tank)
EntityType t\corpus,0  
ff.fisik=New fisik
ff\en=CreatePivot() 
PositionEntity ff\en,t\xc,t\yc,t\zc
RotateEntity  ff\en,EntityPitch(t\corpus),EntityYaw(t\corpus),EntityRoll(t\corpus)
ff\mesh=t\corpus
ff\sk=t\z#
ff\gr=4
ff\dgr=-.2
ff\live=300
ff\sl1=Rnd(1,2)
ff\sl2=Rnd(15,20)
ff\tip=0
EntityType ff\en,3
EntityRadius ff\en,20

End Function
;============
Function shadows(t.tank)
  PointEntity shadL,t\corpus,0
 anglX#=EntityPitch(shadL,1)
 anglY#=EntityYaw(shadL,1)-t\rxc
rX#=Abs(Sin(anglY))*10
rz#=Abs(Cos(anglY))*10
 x#=(t\xc-EntityX(shadL,1))/300
 z#=(t\zc-EntityZ(shadL,1))/300
  RotateEntity t\sh,t\rxc+90,t\ryc,t\rzc
  PositionEntity t\sh,t\xc+x,t\yc+3,t\zc+z
 ScaleSprite  t\sh,14+rx,20+rz

End Function
;=================
Function gravity(t.tank)
yter#=Ymesh(t\corpus)
If yter<>1000
  t\dify#=yter-t\yc+t\height
Else
  t\dify#=0
EndIf		
		If t\dify>.1
		  t\gravity=t\gravity+Abs((t\dify*t\dify)/20)
		EndIf
		If t\dify<0
		     t\gravity=t\gravity+t\dify/50
		EndIf	
		t\gravity=t\gravity-(t\gravity*t\gravity)/400
		
TranslateEntity t\corpus,0,t\gravity,0

t\gravity=t\gravity/1.1

End Function
;=============
Function bonus(t.tank,ent)
  For b. bonus=Each bonus
   If b\en=ent
      Select b\tip
        Case 1
               t\bullet=t\bullet+20
          If t\bullet>maxBullet t\bullet=maxBullet
        Case 2
              t\rocket=t\rocket+1
         If t\rocket>maxRocket t\rocket=maxRocket
        Case 3
           t\live=maxlive
                 If command=0       
                   shet(t.tank) 
                 Else
                        shetCom()
                 EndIf
      End Select
      ClearValue (b\en)      
      b\Time=2000
      HideEntity b\en
      dcd= PlaySound (sBon)
      ChannelVolume dcd, audSa / (EntityDistance (t\corpus,cam)/200)
   EndIf
Next 
End Function
;====================
Function Fire1(t.tank)
If t\speed=0
t\al=1.2
t\speed=speedfire
t\bullet=t\bullet-1

If Abs(t\rxc)<5 t\xrrd =-Cos(t\ryt-t\ryc)*3
If Abs(t\rzc)<5 t\zrrd =-Sin(t\ryt-t\ryc)*3

t\guns=PlaySound (sgun)
 ChannelVolume t\guns, audSa / (EntityDistance (t\corpus,cam)/50)

f.fire=New fire
f\en=CopyEntity(FireW)
;EntityOrder f\en,-1
PositionEntity f\en,t\xs,t\ys+5,t\zs
RotateEntity f\en,t\rxs,t\rys,t\rzs
MoveEntity f\en,0,0,20
ScaleSprite f\en,.05,.05
f\sk=speedBullet
f\dgr=-.2
f\live=1000
f\sl1=5
f\sl2=10
f\tip=1
f\ent=t\corpus
EntityType f\en,3
EntityRadius f\en,5
EndIf
End Function
;============
Function Fire2(t.tank)
If t\speed2=0
;t\al=1.2
t\speed2=speedfire*5
t\rocket=t\rocket-1
t\guns=PlaySound (sRoc)
ChannelVolume t\guns, audSa / (EntityDistance (t\corpus,cam)/50)

f.fire=New fire
f\en=CopyEntity(rocket)
PositionEntity f\en,t\xs,t\ys+5,t\zs
RotateEntity f\en,t\rxs,t\rys,t\rzs
MoveEntity f\en,0,0,20
f\sk=speedRocket
f\live=100
f\tip=3
f\ent=t\corpus
EntityType f\en,3
EntityRadius f\en,.8

f\target=Naved(f\en,t\corpus,t\Color$)
EndIf
End Function
;===========
Function Naved(ent,ent1,Color$)
 For t.tank=Each tank
  If command=0
   If t\ang=0 And t\corpus<>ent1
     If targetR=0 targetR=t\corpus
     dist1#=EntityDistance(ent,targetR)
     dist2#=EntityDistance(ent,t\corpus)
     If dist1>dist2 targetR=t\corpus
   EndIf 
  Else
    If Color$<>t\Color$
    If t\ang=0 And t\corpus<>ent1
     If targetR=0 targetR=t\corpus
     dist1#=EntityDistance(ent,targetR)
     dist2#=EntityDistance(ent,t\corpus)
     If dist1>dist2 targetR=t\corpus
   EndIf 
   EndIf
  EndIf
 Next
 If targetR<>0 Return targetR
End Function
;================
Function UpdateBonus()
For b. bonus=Each bonus
If b\Time=0
   TurnEntity b\en,0,.6,0
   PositionEntity b\en,EntityX(b\en),EntityY(b\en)+Sin(Time/5)/3,EntityZ(b\en)
Else
  b\Time=b\Time-1
     If b\Time=100  ShowEntity b\en
   If b\Time<101
     EntityAlpha b\en,(100-b\Time)/100    
  EndIf
EndIf
Next
End Function
;===================
Function UpdateFire()
  For f.fire=Each fire
      del=0
   f\live=f\live-1
   If f\live<=0 del=1

;-------------------------
 If f\tip=1
          sc#=Rnd(f\sl1,f\sl2)
          ScaleSprite f\en,sc,sc
          RotateSprite f\en,f\live*300
       ccv=Rand(100,255)  
       EntityColor f\en,255,ccv,ccv
       sleif(f\en)
 EndIf

If f\tip=2
          sc#=Rnd(f\sl1,f\sl2)
          ScaleSprite f\en,sc,sc
          RotateSprite f\en,f\live*300
       sleifS(f\en)
 EndIf
If f\tip=3

If f\target<>0
dangl#=DeltaYaw(f\en,f\target)
     If dangl>0
        TurnEntity f\en,0,6,0
      Else
         TurnEntity f\en,0,-6,0
      EndIf
dangl#=DeltaPitch(f\en,f\target)
     If dangl>0
       TurnEntity f\en,6,0,0
     Else
        TurnEntity f\en,-6,0,0
     EndIf
EndIf
       sleifR(f\en)
EndIf
;--------------------------  
  ent=0
   ent1=0
   ent=EntityCollided(f\en,1)
   ent1=EntityCollided(f\en,5)
   If ent<>0 Or ent1<>0
        del=1
           If EntityInView ( f\en,cam )
                 bum1a(f\en) 
                  If f\tip=3   bum1b(f\en)
             EndIf
         bah=PlaySound (sbah)
         ChannelVolume bah, audSa / (EntityDistance (f\en,cam)/500)
   EndIf

;--------------------------  
  ent=0
   ent=EntityCollided(f\en,2)
   If ent<>0
        del=1
        If EntityInView ( f\en,cam )
                  bum1(f\en):sled(1,EntityX(f\en),EntityZ(f\en),terrain)  
            If f\tip=3   bum1b(f\en)
         EndIf

       bah=PlaySound (sbah)
       ChannelVolume bah, audSa / (EntityDistance (f\en,cam)/500)  
   EndIf
;--------------------------  
  ent=0
   ent=EntityCollided(f\en,4)
   If ent<>0
        del=1
           
        For t.tank=Each tank
            If f\ent=t\corpus
                 t\point=t\point+5 
              If command=0       
              shet(t.tank) 
              Else
                    If EntityName(ent)<>EntityName(f\ent)
                    If t\Color$="red" Redpoint=Redpoint+5
                    If t\Color$="blue" bluepoint=bluepoint+5
                       shetCom()
                         Else
                           t\point=t\point-10
                           If t\point<0 t\point=0
                        EndIf
           EndIf
           If chall=1 shetAll()
            EndIf 
         If t\corpus=ent And f\ent<>t\corpus
         t\live=t\live-1
        t\DamageYaw=DeltaYaw(t\corpus,f\en)
        If f\tip=3 t\live=0
         If t\live=0 
         deatch(f\ent,ent)
              Else
                If Abs(t\rxc)<10  t\xrrd =-Cos(-EntityYaw(f\en)-t\ryc)*2
                If Abs(t\rzc)<10  t\zrrd =-Sin(-EntityYaw(f\en)--t\ryc)*2
                   TranslateEntity t\corpus,0,1,0
                If t\live>0  TextureMesh(t\corpus,Damage,1)
              EndIf          
         EndIf
       Next


         EntityType f\en,0
         ;MoveEntity f\en,0,0,f\sk
      ;If EntityInView ( f\en,cam ) 
          bum2(f\en)
   EndIf
;==========================
MoveEntity f\en,0,0,f\sk
TranslateEntity f\en,0,f\gr,0
f\gr=f\gr+f\dgr
     ;------------------------
  If del=1 
    If f\tip=3 
           bah=PlaySound (sbah)
        ChannelVolume bah, audSa / (EntityDistance (f\en,cam)/500)
          bum1b(f\en)
      EndIf
    FreeEntity f\en
    Delete f
  EndIf
Next

End Function
;==================
Function UpdateFlash()
 For b.Flash=Each Flash
     b\liv=b\liv-1
     b\gr=b\gr-b\dgr
     If b\liv=0  b\tip=2
    TranslateEntity b\en,0,b\gr,0
   Select b\tip
    Case 0 
      b\sc=b\sc+b\sc*b\dsc
      If b\sc>b\psc b\tip=1

       Case 1 
      b\sc=b\sc+b\sc*b\ssc
      If b\sc<.1 b\tip=2

       Case 3
      MoveEntity b\en,b\sc/5,b\sc/5,5
      b\sc=b\sc+b\sc*b\dsc
      If b\sc>b\psc b\tip=1

    End Select
 
 ScaleSprite b\en,b\sc,b\sc
    If b\tip=2
      FreeEntity b\en
      Delete b
    EndIf
 Next
End Function
;========================
Function UpdateAnim()
For a.anim=Each anim
del=0
a\liv=a\liv-1
;---------------------
If a\dsc>1 
  a\dsc=a\dsc-.01
 a\sc=a\sc*a\dsc
EndIf

;--------------------
EntityTexture a\en,a\Tex,a\frame
EntityAlpha a\en,a\al

;---------------------------------------
Select a\tip
Case 0
ScaleSprite a\en,a\sc,a\sc
Case 1
ScaleSprite a\en,a\sc,a\sc*2
TranslateEntity a\en,0,-.7,0
If a\al>0 a\al=a\al-a\dal
End Select
;---------------------------
a\frame=a\frame+a\dfr
If a\frame>a\fr del=1
;-----------------------------
If del=1
 FreeEntity a\en
 Delete a
EndIf

Next
End Function
;========================
Function updateSprite()
maxsmok=0
For s.part=Each part
maxsmok=maxsmok+1
If maxsmok>300 s\live=-1
           If s\al<-.0003 Or s\live<0
		 	FreeEntity s\en
			Delete s
			Goto ef	
		Else
		      EntityAlpha s\en,s\al
		      ScaleSprite s\en,s\sc,s\sc
		      TranslateEntity s\en,0,s\gr,0
		      MoveEntity s\en,0,0,s\skr
		
		 
		     s\al=s\al+s\dal
		   s\gr=s\gr+s\dgr
		   If s\skr>0   s\skr=s\skr+s\dskr
		   s\live=s\live-s\dlive
		Select s\tip
		    Case 1
		        s\sc=s\sc+s\sc/s\dsc
		        s\dsc=s\dsc*1.03 
		    Case 2
		         s\sc=s\sc+s\sc/s\dsc
		       Case 3
		         s\sc=s\sc+s\sc/s\dsc 
		         If  s\cv>0 s\cv=s\cv-5      
		         EntityColor s\en,s\cv,s\cv/1.5,0
		    Case 4 
		    s\rot=s\rot+s\drot
		    RotateSprite s\en,s\rot
		
		    s\sc=s\sc+(s\sc/s\dsc)*(s\al*s\al)
		    ;sparc(s\en)
		  
		  End Select       
		        
		  			      		 		
EndIf      
.ef
Next
End Function
;----------------------------------
Function UpdateOsk()
    maxosk=0
  For o.osk=Each osk
   del=0
     maxosk=maxosk+1
     If maxosk>100 del=1
   o\live=o\live-1
   If o\live<=0 del=1
;==========================
MoveEntity o\en,0,0,o\sk
TranslateEntity o\en,0,o\gr,0

Select o\tip
Case 0
o\gr=o\gr+o\dgr
o\sk=o\sk/1.02
TurnEntity o\mesh,o\sl1,o\sl2,0
Case 1 
 o\gr=o\gr+o\dgr
o\sk=o\sk/1.2
TurnEntity o\mesh,o\sl1,o\sl2,0
Case 2
o\gr=o\gr+o\dgr
o\sk=o\sk/1.02
RotateSprite o\mesh,o\sk*2
Case 3
o\gr=o\gr+o\dgr
o\sk=o\sk/1.02
RotateSprite o\mesh,o\sl1
o\sl1=o\sl1+o\sl2
If o\live>90 sleifA(o\en)
End Select
     ;------------------------
  If del=1
      FreeEntity o\en
    Delete o
  EndIf
Next

End Function
;====================
Function UpdateFisik(t.tank)
  For ff.fisik=Each fisik
If ff\mesh=t\corpus
   del=0
;==========================
Select ff\tip
Case 0
MoveEntity ff\en,0,0,ff\sk
TranslateEntity ff\en,0,ff\gr,0

 ff\gr= ff\gr+ ff\dgr
 ff\sk= ff\sk/1.01
 ff\sl1= ff\sl1/1.01
 ff\sl2= ff\sl2/1.01
TurnEntity  ff\mesh, ff\sl1,0,ff\sl2

PositionEntity  ff\mesh,EntityX( ff\en),EntityY( ff\en),EntityZ( ff\en)
ent=0
ent=EntityCollided( ff\en,4)
If EntityCollided( ff\en,2) Or EntityCollided( ff\en,1) Or EntityCollided( ff\en,5) Or ent<>0
If ent<>0 Or t\live<-200
  ;ent1=t\corpus
  deatchFisik(ent)
  EndIf  
  If EntityInView ( t\corpus,cam )  bum3(t\corpus)
  bah=PlaySound (sbah3)
  ChannelVolume bah, audSa / (EntityDistance (ff\en,cam)/500)
  HideEntity t\corpus
  sled(2,t\xc,t\zc,terrain)
    t\live=-251
   del=1
EndIf
End Select 

     ;------------------------
  If del=1
      FreeEntity  ff\en
    Delete  ff
  EndIf
EndIf
Next

End Function
;================
Function deatchFisik(ent)
For t.tank=Each tank
  If t\corpus=ent
       
        t\live=0
           deatch(ent1,ent)           
  EndIf

  Next
End Function
;--------------------------------------
Function bum1a(ent)
osk(ent,1)
 xv#=EntityX(ent)
 yv#=EntityY(ent)
 zv#=EntityZ(ent)
;----------------------------
a.Anim=New Anim
a\en=CopyEntity(boom)
a\fr=15
a\Tex=texboom
a\dfr=.9
PositionEntity a\en,xv,yv+20,zv
a\sc=20
a\dsc=1
a\al=1
a\dal=0
a\liv=1200
PointEntity a\en,cam
MoveEntity a\en,0,0,10
;-------------------------
For i=1 To effect

s.part=New part
s\en=CopyEntity(Smoke)

PositionEntity s\en,xv+Rnd(-1,1),yv,zv+Rnd(-1,1)

RotateEntity s\en,0,Rand(0,360),0
TurnEntity s\en,Rand(-90,-20),0,0

RotateSprite s\en,Rand(0,360)
s\cv=Rand(10,150)
EntityColor s\en,s\cv,s\cv,s\cv
s\sc=40
s\dsc#=30
s\al=1
s\dal=-.06/effect
s\gr=.5

s\tip=1
s\skr=i*2
s\dskr=-s\skr/20
ScaleSprite s\en,s\sc,s\sc
Next

End Function
;=============
Function bum1b(ent)
xv#=EntityX(ent)
 yv#=EntityY(ent)
 zv#=EntityZ(ent)
osk(ent,2)
a.Anim=New Anim
a\en=CopyEntity(boom)
a\fr=15
a\Tex=texboom
a\dfr=.4
PositionEntity a\en,xv,yv+20,zv
a\sc=40
a\dsc=1
a\al=1
a\dal=0
a\liv=1200
PointEntity a\en,cam
MoveEntity a\en,0,0,10

End Function
;=================
Function bum1(ent)


;oskA(ent,3)
osk(ent,2)

 xv#=EntityX(ent)
 yv#=EntityY(ent)
 zv#=EntityZ(ent)
;----------------------------
a.Anim=New Anim
a\en=CopyEntity(boom)
a\fr=15
a\Tex=texboom
a\dfr=.9
PositionEntity a\en,xv,yv+20,zv
a\sc=20
a\dsc=1
a\al=1
a\dal=0
a\liv=1200
PointEntity a\en,cam
MoveEntity a\en,0,0,10

;-----------------------------
a.Anim=New Anim
a\fr=28.5
a\en=CopyEntity(boom1)
a\Tex=texboom1
a\dfr=.6
;EntityOrder b\en,-1
EntityColor a\en,100,100,100 
PositionEntity a\en,xv,yv+20,zv
a\sc=60
a\dsc=1.1
a\al=1
a\dal=.02
a\liv=1200
a\tip=1
ScaleSprite a\en,a\sc,a\sc
;PointEntity a\en,cam
;MoveEntity a\en,0,0,10
;-------------------------
For i=1 To effect

s.part=New part
s\en=CopyEntity(Smoke)

PositionEntity s\en,xv+Rnd(-1,1),yv,zv+Rnd(-1,1)

RotateEntity s\en,0,Rand(0,360),0
TurnEntity s\en,Rand(-90,-20),0,0

RotateSprite s\en,Rand(0,360)
s\cv=Rand(10,150)
EntityColor s\en,s\cv,s\cv,s\cv
s\sc=40
s\dsc#=30
s\al=1
s\dal=-.05/effect
s\gr=.5

s\tip=1
s\skr=i*2
s\dskr=-s\skr/20
ScaleSprite s\en,s\sc,s\sc
Next

End Function
;=================
Function bum2(ent)
bah=PlaySound (sbah)
 ChannelVolume bah, audSa / (EntityDistance (ent,cam)/500)
 xv#=EntityX(ent)
 yv#=EntityY(ent)
 zv#=EntityZ(ent)

a.Anim=New Anim
a\en=CopyEntity(booma)
a\fr=15
a\Tex=texbooma
a\dfr=.9
;EntityOrder b\en,-1
PositionEntity a\en,xv,yv,zv
a\sc=60
a\dsc=1
a\al=1
a\dal=0
a\liv=1200
PointEntity a\en,cam
MoveEntity a\en,0,0,10

;--------------------------
For i=1 To 7*effect
f.fire=New fire
f\en=CopyEntity(Spark)

PositionEntity f\en,xv,yv,zv
TurnEntity  f\en,0,Rand(0,360),0
TurnEntity  f\en,Rand(-20,-89),0,0
ss#=Rand(2,8)
f\sk=Rand(10,30)
f\gr=0
f\dgr=-2
f\live=10

f\sl1=ss
f\sl2=ss
f\tip=2
ScaleSprite f\en,ss,ss


Next


For i=1 To effect

s.part=New part
s\en=CopyEntity(Smoke)

PositionEntity s\en,xv+Rnd(-1,1),yv+Rnd(-1,1),zv+Rnd(-1,1)

RotateEntity s\en,0,Rand(0,360),0
TurnEntity s\en,Rand(-90,0),0,0

RotateSprite s\en,Rand(0,360)
s\cv=Rand(230,250)
EntityColor s\en,s\cv,s\cv/2,0
;EntityBlend s\en,3

s\sc=20
s\dsc#=60
s\al=.5
s\dal=-.015/effect
s\gr=.5

s\tip=1
s\skr=7
s\dskr=-1
s\tip=3
ScaleSprite s\en,s\sc,s\sc
Next

End Function
;=================
Function bum3(ent)
bah=PlaySound (sbah3)
 ChannelVolume bah, audSa / (EntityDistance (ent,cam)/500)
 xv#=EntityX(ent)
 yv#=EntityY(ent)
 zv#=EntityZ(ent)

a.Anim=New Anim
a\en=CopyEntity(boom)
a\fr=15
a\Tex=texboom
a\dfr=.6
;EntityOrder b\en,-1
PositionEntity a\en,xv,yv+50,zv
a\sc=140
a\dsc=1
a\al=1
a\dal=0
a\liv=1200
PointEntity a\en,cam
MoveEntity a\en,0,0,10
;--------------------------
 oskT(ent,9)
For i=1 To 20

s.part=New part

iw=Rand(0,1)
If iw=0
  s\en=CopyEntity(Smoke)
  s\cv=Rand(13,15)
  EntityColor s\en,s\cv,s\cv/2,0
  s\sc=30
  s\al=.8
  s\dal=-.04/effect
s\tip=3
Else
   s\en=CopyEntity(Fire)
   EntityBlend s\en,3
   s\cv=850
   s\sc=20
   s\al=.2
   s\dal=-.015/effect 
    ;s\drot=Rand(-4,4)
s\tip=4
EndIf

PositionEntity s\en,xv+Rnd(-20,20),yv+Rnd(1,10),zv+Rnd(-20,20)
RotateEntity s\en,0,Rand(0,360),0
TurnEntity s\en,Rand(-90,0),0,0
RotateSprite s\en,Rand(0,360)

s\dsc#=60
s\gr=1

;s\tip=1
s\skr=9
s\dskr=-1

ScaleSprite s\en,s\sc,s\sc
Next

End Function
Function bum3a(ent)
bah=PlaySound (sbah3)
 ChannelVolume bah, audSa / (EntityDistance (ent,cam)/500)
 xv#=EntityX(ent)
 yv#=EntityY(ent)
 zv#=EntityZ(ent)

a.Anim=New Anim
a\en=CopyEntity(boom)
a\fr=15
a\Tex=texboom
a\dfr=.6
;EntityOrder b\en,-1
PositionEntity a\en,xv,yv+50,zv
a\sc=100
a\dsc=1
a\al=1
a\dal=0
a\liv=1200
PointEntity a\en,cam
MoveEntity a\en,0,0,10
;--------------------------
 oskTa(ent,3)
For i=1 To 20

s.part=New part

iw=Rand(0,1)
If iw=0
  s\en=CopyEntity(Smoke)
  s\cv=Rand(13,15)
  EntityColor s\en,s\cv,s\cv/2,0
  s\sc=30
  s\al=.8
  s\dal=-.04/effect
s\tip=3
Else
   s\en=CopyEntity(Fire)
   EntityBlend s\en,3
   s\cv=850
   s\sc=20
   s\al=.2
   s\dal=-.015/effect 
    ;s\drot=Rand(-4,4)
s\tip=4
EndIf

PositionEntity s\en,xv+Rnd(-20,20),yv+Rnd(1,10),zv+Rnd(-20,20)
RotateEntity s\en,0,Rand(0,360),0
TurnEntity s\en,Rand(-90,0),0,0
RotateSprite s\en,Rand(0,360)

s\dsc#=60
s\gr=1

;s\tip=1
s\skr=9
s\dskr=-1

ScaleSprite s\en,s\sc,s\sc
Next

End Function

;=============
Function effectKoleso(t.tank)
     xv#=EntityX(t\wh3,1)
    yv#=EntityY(t\wh3,1)
   zv#=EntityZ(t\wh3,1)

 f.fire=New fire
f\en=CopyEntity(zeml)
RotateSprite f\en,Rand(0,360)
PositionEntity f\en,xv,yv-3,zv
RotateEntity f\en,t\rxc,t\ryc,t\rzc
If t\z>0 TurnEntity f\en,0,180,0
TurnEntity f\en,Rand(-10,-35),0,0
EntityAlpha f\en,.6
cv=Rand(0,255)
EntityColor f\en,cv,cv,cv
ScaleSprite f\en,6,6

f\sk=Rnd(1,3)
f\dgr=-1.5
f\live=10

f\sl1=2
f\sl2=5
f\tip=10
f\ent=t\corpus
MoveEntity f\en,0,0,4

xv#=EntityX(t\wh4,1)
    yv#=EntityY(t\wh4,1)
   zv#=EntityZ(t\wh4,1)

 f.fire=New fire
f\en=CopyEntity(zeml)
RotateSprite f\en,Rand(0,360)
PositionEntity f\en,xv,yv-3,zv
RotateEntity f\en,t\rxc,t\ryc,t\rzc
If t\z>0 TurnEntity f\en,0,180,0
TurnEntity f\en,Rand(-10,-35),0,0
EntityAlpha f\en,.6
cv=Rand(0,255)
EntityColor f\en,cv,cv,cv
ScaleSprite f\en,6,6

f\sk=Rnd(1,3)
f\dgr=-1.5
f\live=10

f\sl1=2
f\sl2=5
f\tip=10
f\ent=t\corpus
MoveEntity f\en,0,0,4
End Function
;=================

Function sparc(ent)
 xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
   xr#=EntityPitch(ent)
   yr#=EntityYaw(ent)
   zr#=EntityRoll(ent)
f.fire=New fire
f\en=CopyEntity(Sparks)

PositionEntity f\en,xv,yv,zv
TurnEntity  f\en,0,Rand(0,360),0
TurnEntity  f\en,Rand(-90,0),0,0

f\sk=6
f\gr=0
f\dgr=-.2
f\live=100
ss#=Rnd(.5,4)
f\sl1=ss
f\sl2=ss
f\tip=0
ScaleSprite f\en,ss,ss

End Function
;================
Function osk(ent,kol)
k=1
For i=1 To kol*(effect-1)
 xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
   xr#=EntityPitch(ent)
   yr#=EntityYaw(ent)
   zr#=EntityRoll(ent)
o.osk=New osk
o\en=CreatePivot() 
o\mesh=CopyEntity(osk)
ShowEntity o\mesh

PositionEntity o\en,xv,yv+10,zv
PositionEntity o\mesh,xv,yv+10,zv
TurnEntity  o\en,0,Rand(0,360),0
TurnEntity  o\en,Rand(-60,-5),0,0
EntityParent o\mesh,o\en

o\sk=Rand(5,12)
o\gr=0
o\dgr=-.15
o\live=100
ss#=Rnd(1,5)
k=-k
o\sl2=Rand(10,20)*k
o\tip=3
ScaleSprite o\mesh,ss,ss
RotateSprite o\mesh,Rand(0,360)

Next
End Function
;====================
Function oskT(ent,kol)
xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
   xr#=EntityPitch(ent)
   yr#=EntityYaw(ent)
   zr#=EntityRoll(ent)

For i=1 To kol*effect
    
o.osk=New osk
o\en=CreatePivot() 

iw=Rand(1,3)

Select iw
Case 1
o\mesh=CopyEntity(oskT1)
Case 2
o\mesh=CopyEntity(oskT2)
Case 3
o\mesh=CopyEntity(oskT3)
End Select


EntityParent o\mesh,o\en
PositionEntity o\en,xv,yv,zv
TurnEntity  o\en,0,Rand(0,360),0
TurnEntity  o\en,Rand(-80,-5),0,0
ShowEntity o\mesh

o\sk=Rand(4,12)
o\gr=0
o\dgr=-.1
o\live=90
ss#=Rnd(2,5)
o\sl1=Rand(2,20)
o\sl2=Rand(2,20)
o\tip=0
ScaleEntity o\en,ss,ss,ss

Next
For i=1 To 4
o.osk=New osk
o\en=CreatePivot() 
o\mesh=CopyEntity(oskT4)
EntityParent o\mesh,o\en
PositionEntity o\en,xv,yv,zv
TurnEntity  o\en,0,Rand(0,360),0
TurnEntity  o\en,Rand(-80,-5),0,0
ShowEntity o\mesh

o\sk=Rand(4,6)
o\gr=0
o\dgr=-.1
o\live=90
o\sl1=Rand(10,20)
o\sl2=Rand(10,20)
o\tip=0
Next
End Function
;=============
Function oskTa(ent,kol)
xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
   xr#=EntityPitch(ent)
   yr#=EntityYaw(ent)
   zr#=EntityRoll(ent)

For i=1 To kol*effect
    
o.osk=New osk
o\en=CreatePivot() 

iw=Rand(1,3)

Select iw
Case 1
o\mesh=CopyEntity(oskT1)
Case 2
o\mesh=CopyEntity(oskT2)
Case 3
o\mesh=CopyEntity(oskT3)
End Select


EntityParent o\mesh,o\en
PositionEntity o\en,xv,yv,zv
TurnEntity  o\en,0,Rand(0,360),0
TurnEntity  o\en,Rand(-80,-5),0,0
ShowEntity o\mesh

o\sk=Rand(4,12)
o\gr=0
o\dgr=-.1
o\live=90
ss#=Rnd(2,5)
o\sl1=Rand(2,20)
o\sl2=Rand(2,20)
o\tip=0
ScaleEntity o\en,ss,ss,ss
Next
End Function

;====================

Function sleif(ent)

   xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
s.part=New part
s\en=CopyEntity(Smoke)

PositionEntity s\en,xv,yv,zv

RotateSprite s\en,Rand(0,360)

EntityColor s\en,155,120,120
EntityBlend s\en,3
s\sc=5
s\dsc#=30
s\al=.6
s\dal=-.24/effect
;s\gr=.01

s\tip=1
s\skr=0
s\dskr=1
ScaleSprite s\en,s\sc,s\sc
End Function
;====================

Function sleifR(ent)

   xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
s.part=New part
s\en=CopyEntity(Smoke)

PositionEntity s\en,xv,yv,zv

RotateSprite s\en,Rand(0,360)

EntityColor s\en,255,120,200
EntityBlend s\en,3
s\sc=5
s\dsc#=10
s\al=1
s\dal=-.18/effect
s\tip=1
s\skr=0
s\dskr=0
ScaleSprite s\en,s\sc,s\sc
End Function
;============
Function sleifS(ent)
   xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
s.part=New part
s\en=CopyEntity(Spark)

PositionEntity s\en,xv,yv,zv

RotateSprite s\en,Rand(0,360)

EntityColor s\en,155,120,120
EntityBlend s\en,3
s\sc=2
s\dsc#=-30
s\al=1
s\live=3*effect
s\dlive=1
;s\dal=-.06/effect
;s\gr=-1

s\tip=1
s\skr=0
s\dskr=1
ScaleSprite s\en,s\sc,s\sc
End Function

;==================
Function sleifA(ent)
   xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
s.part=New part
s\en=CopyEntity(Smoke)

PositionEntity s\en,xv,yv,zv

RotateSprite s\en,Rand(0,360)

EntityColor s\en,255,120,0
EntityBlend s\en,3
s\sc=5
s\dsc#=30
s\al=.6
s\dal=-.06/effect
;s\gr=.01

s\tip=1
s\skr=0
s\dskr=1
ScaleSprite s\en,s\sc,s\sc
End Function

;==========================
Function dump(ent,sc#)
   xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
s.part=New part
s\en=CopyEntity(Smoke)

PositionEntity s\en,xv+Rand(-5,5),yv,zv+Rand(-5,5)

RotateSprite s\en,Rand(0,360)

EntityColor s\en,50,12,0
;i=Rand(0,3)

;If i<>0
 EntityColor s\en,255,120,0:EntityBlend s\en,3

s\sc=sc
s\dsc#=30
s\al=1
s\dal=-.03/effect
s\gr=.5

s\tip=1
s\skr=0
s\dskr=1
ScaleSprite s\en,s\sc,s\sc
End Function
;====================
Function Ymesh(ent)
PositionEntity pivot,EntityX(ent,1),EntityY(ent,1)+10,EntityZ(ent,1)
RotateEntity pivot,90,0,0,1
pic=EntityPick (pivot,380)

            If pic=0
              TranslateEntity ent,0,-2,0
               ypick#=1000
            Else
              If EntityName(pic)="terrain" 
                        ypick#=PickedY#() 
                     Else
                       ypick#=1000
                     EndIf  
            EndIf
       Return ypick
End Function
;=========================
Function rotateAtPosition(t.tank)
EntityPickMode t\corpus,0
kr#=15
pic1=0
pic2=0
pic3=0
pic4=0
PositionEntity pivot,t\xc,t\yc+20,t\zc
RotateEntity pivot,0,t\ryc,0
MoveEntity pivot,-kr/2,0,0
RotateEntity pivot,90,0,0

pic1=EntityPick (pivot,170)
If pic1<>0 terE#= PickedY#()

RotateEntity pivot,0,t\ryc,0
MoveEntity pivot,kr,0,0 
RotateEntity pivot,90,0,0

pic2=EntityPick (pivot,170)
If pic2<>0 ter2#= PickedY#()-terE#

PositionEntity pivot,t\xc,t\yc+20,t\zc
RotateEntity pivot,0,t\ryc,0
MoveEntity pivot,0,0,-kr/2
RotateEntity pivot,90,0,0
pic3=EntityPick (pivot,170)
If pic3<>0 terEt#= PickedY#()

PositionEntity pivot,t\xc,t\yc+20,t\zc
RotateEntity pivot,0,t\ryc,0
MoveEntity pivot,0,0,kr/2
RotateEntity pivot,90,0,0
pic4=EntityPick (pivot,170)
;==================
If pic4<>0 ter1#= PickedY#()-terEt#

If pic1<>0 And pic2<>0 And pic3<>0 And pic4<>0
    xrapp#=-ATan(ter1/kr)
    zrapp#=ATan(ter2/kr)

If  Abs(t\dify)<8 
     deltaX#=(xrapp-t\rxc)
     deltaz#=(zrapp-t\rzc)/4
Else
    deltaX#=(xrapp-t\rxc)/10
   deltaz#=(zrapp-t\rzc)/10

EndIf

If t\z>0
        If deltaX#>0    
            t\xrrd=t\xrrd+deltax/20
        Else 
            t\xrrd=t\xrrd+deltax*.7
       EndIf
Else
   If deltaX#<0 
     t\xrrd=t\xrrd+deltax/20
  Else 
    t\xrrd=t\xrrd+deltax*.7
  EndIf

EndIf 
  t\zrrd=t\zrrd+deltaz  
 ; If t\xrrd<-30 t\xrrd=-30    
 ; If t\xrrd>30 t\xrrd=30
EndIf
TurnEntity t\corpus,t\xrrd,0,t\zrrd
 If t\z>0 And deltaX#<0 t\xrrd=t\xrrd/2
  If t\z<0 And deltaX#>0 t\xrrd=t\xrrd/2
  
 t\xrrd=t\xrrd/1.1
 t\zrrd=t\zrrd/1.4
EntityPickMode t\corpus,1,0
End Function
;====================

;======================
Function kl(ent,cm#)
 xv#=EntityX(ent,1)
 yv#=EntityY(ent,1)
 zv#=EntityZ(ent,1)
  xrv#=EntityPitch(ent,1)
 yrv#=EntityYaw(ent,1)
 zrv#=EntityRoll(ent,1)
s.part=New part
s\en=CopyEntity(Smoke)
PositionEntity s\en,xv+Rnd(-1,1),yv+Rnd(1,10),zv+Rnd(-1,1)
RotateEntity s\en,xrv,yrv,zrv
MoveEntity s\en,0,0,20+cm 
TurnEntity s\en,0,Rand(-9,9),0

RotateSprite s\en,Rand(0,360)
s\cv=Rand(200,250)
EntityColor s\en,s\cv,s\cv,s\cv
EntityParent s\en,ent

s\sc=6
s\dsc#=20
s\al=.4
s\dal=-.06
;s\gr=1

s\tip=1
s\skr=1
s\dskr=-.2
ScaleSprite s\en,s\sc,s\sc

End Function 
;---------------------------------
Function inf(t.tank)
    SetFont fon4
   t\texa=CreateTexture(128,128)
   t\inf=CreateSprite()
     
   EntityTexture t\inf,t\texa
   EntityBlend t\inf,3
    EntityFX t\inf,8
Cls
  SetBuffer TextureBuffer(t\texa)
   Color 255,255,255
   Text 1,5,t\name$

  If command=1
    If t\Color$="red" Color 255,55,0
    If t\Color$="blue" Color 0,55,255
      Rect 0,40,StringWidth (t\name$),3
  EndIf
 ;Stop
    ;Text 0,0,t\name$
   ; Text 0,2,t\name$
   ; Text 2,0,t\name$
   ; Text 2,2,t\name$
   
     ;Color 0,0,0
     
  SetBuffer BackBuffer()
EntityOrder t\inf,-1
End Function

Function texture(ent)
For i=1 To CountSurfaces(ent)
  surf=GetSurface(ent,i)
 brush= GetSurfaceBrush(surf)
  texture=GetBrushTexture(brush)
  FreeBrush brush
 If texture<>0 Return texture
Next
End Function

Function sled(tip,xs#,zs#,ent)

cctex=0
For ta.ter=Each ter
If ta\en=ent
cctex=ta\Tex

w#=MeshWidth(ent)
h#=MeshDepth (ent)

xs=xs-EntityX(ent)

zs=zs-EntityZ(ent)

xs=xs+w/2
zs=zs+h/2

x#=xs*(ta\sizew/w)
z#=zs*(ta\sizeh/h)

;------------------
EndIf
Next

;----------------
If cctex<>0 
If cctex<>0
 SetBuffer TextureBuffer( Cctex )
Color 20,20,20
Plot x,z
If tip=2
Plot x-1,z
Plot x,z-1
Plot x-1,z-1
EndIf
;-------------------------
SetBuffer BackBuffer()

EndIf

EndIf
End Function
;=========================

Function respawn(t.tank) 
If parent=t\corpus detP=0
  t\xres=t\xres+1
  If t\xres=5 t\xres=1
   For a.angar=Each angar
    If a\kol=t\xres
      PositionEntity t\corpus,a\x,a\y-20,a\z
         t\yc=a\y-20
    EndIf
   Next



  
   PointEntity t\corpus,centr,0
EntityType t\corpus,4
     PointEntity t\turret,centr,0
    PointEntity t\stvol,centr,0
   ShowEntity t\turret
   ShowEntity t\stvol
   ShowEntity t\corpus
      
      ShowEntity t\sh
    t\angl=0
   
  t\live=maxlive
t\ang=200
TextureMesh(t\corpus,DamageNo,1)
 If rad=1
     ShowEntity t\pivot
     ShowEntity  t\pivote
   EndIf  

If command=0       
  shet(t.tank) 
Else
  shetCom()
EndIf
        
End Function
;======================
Function  ClearValue ( x% )
	For s.script = Each script
		For k = 0 To s\intVarC - 1
			If PeekInt ( s\var, k*4 ) = x PokeInt s\var, k*4, 0
		Next
	Next
	InitTankScripts ( )
End Function
;=========================
Function TextureMesh(entity,texture,sl)
 For i=1 To CountChildren(entity)
 child=GetChild(entity,i)
If EntityName(child)<>"inf" And EntityName(child)<>"Gun"  EntityTexture child,texture,0,sl
  If CountChildren(child)>0
     TextureMesh(child,texture,sl)
  EndIf
Next 
End Function
;=========================
Function TypeMesh(entity,tip)
 For i=1 To CountChildren(entity)
 child=GetChild(entity,i)
If EntityName(child)<>"inf"  EntityType child,tip
  If CountChildren(child)>0
     TypeMesh(child,tip)
  EndIf
Next 
End Function
;=========================
Function MakeSkyBox( File$ )
	mw=CreateMesh()
	;front face
	bw=LoadBrush( File$+"_FR.jpg",49 )
	sw=CreateSurface( mw,bw )
	AddVertex sw,-1,+1,-1,0,0:AddVertex sw,+1,+1,-1,1,0
	AddVertex sw,+1,-1,-1,1,1:AddVertex sw,-1,-1,-1,0,1
	AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	FreeBrush bw
	;right face
	bw=LoadBrush( File$+"_LF.jpg",49 )
	sw=CreateSurface( mw,bw )
	AddVertex sw,+1,+1,-1,0,0:AddVertex sw,+1,+1,+1,1,0
	AddVertex sw,+1,-1,+1,1,1:AddVertex sw,+1,-1,-1,0,1
	AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	FreeBrush bw
	;back face
	bw=LoadBrush( File$+"_BK.jpg",49 )
	sw=CreateSurface( mw,bw )
	AddVertex sw,+1,+1,+1,0,0:AddVertex sw,-1,+1,+1,1,0
	AddVertex sw,-1,-1,+1,1,1:AddVertex sw,+1,-1,+1,0,1
	AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	FreeBrush bw
	;left face
	bw=LoadBrush( File$+"_RT.jpg",49 )
	sw=CreateSurface( mw,bw )
	AddVertex sw,-1,+1,+1,0,0:AddVertex sw,-1,+1,-1,1,0
	AddVertex sw,-1,-1,-1,1,1:AddVertex sw,-1,-1,+1,0,1
	AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	FreeBrush bw
	;top face
	bw=LoadBrush( File$+"_UP.jpg",49 )
	sw=CreateSurface( mw,bw )
	AddVertex sw,-1,+1,+1,0,1:AddVertex sw,+1,+1,+1,0,0
	AddVertex sw,+1,+1,-1,1,0:AddVertex sw,-1,+1,-1,1,1
	AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	FreeBrush bw
     ;down face
	bw=LoadBrush( File$+"_DW.jpg",49 )
	sw=CreateSurface( mw,bw )
	AddVertex sw,-1,-1,+1,1,0:AddVertex sw,+1,-1,+1,1,1
	AddVertex sw,+1,-1,-1,0,1:AddVertex sw,-1,-1,-1,0,0
	AddTriangle sw,0,2,1:AddTriangle sw,0,3,2
	FreeBrush bw

	ScaleMesh mw,10,10,10
	FlipMesh mw
	Return mw
	
End Function

Function NextCam()
cam()
   mdf=mdf +1
  If mdf=3 mdf=0
If mdf=0  parent=0:Goto g
If parent=0 firstPar()
For t.tank=Each tank
If t\corpus=parent Or t\turret=parent   
  If t=Null parent=0:firstPar():Goto g
  If mdf=1 parent=t\corpus 
  If mdf=2 parent=t\corpus
EndIf
Next
.g
End Function
;===============
Function NextCamP()
    cam()
   mdf=mdf +1
For t.tank=Each tank
If t\name$=namePl$   
  parent=t\corpus  
EndIf
Next
End Function
;====================
Function NextPar()
For t.tank=Each tank
If t\corpus=parent Or t\turret=parent
     t=After t   
    If t=Null parent=0:firstPar():Goto g
      If mdf=1 parent=t\corpus
      If mdf=2 parent=t\corpus
EndIf
Next
.g
End Function

Function cam()
   EntityParent campiv,0
   EntityParent cam,0
   PositionEntity campiv,EntityX(cam),EntityY(cam),EntityZ(cam)
    RotateEntity campiv,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
   EntityParent cam,campiv
End Function

Function firstPar()
  t.tank=First tank
  If mdf=1  parent=t\corpus
  If mdf=2 parent=t\corpus
End Function

Function CreateMenu()

  fps=50
  period = 1000 / FPS
;EntityRadius cam,0

EntityParent cam,0
RotateEntity cam,0,0,0
PositionEntity cam,0,600,0

PositionEntity pricm,EntityX(cam,1),EntityY(cam,1),EntityZ(cam,1)
RotateEntity pricm,EntityPitch(cam,1),EntityYaw(cam,1),0
MoveEntity pricm,0,0,20

;EntityParent pric,cam


Collisions 4,1,2,3
Collisions 4,4,1,2
bwig=wig
bhei=hei
CameraRange cam,.01,25
;-------------------------------knopka
knp=LoadMesh("menu\kp1.b3d")
knl=LoadMesh("menu\kl1.b3d")
HideEntity knp
HideEntity knl
;-------------------------------------osk robot
or1=LoadMesh("menu\1r.b3d")
or2=LoadMesh("menu\2r.b3d")
or3=LoadMesh("menu\3r.b3d")
or4=LoadMesh("menu\4r.b3d")

ScaleEntity or1,.03,.03,.03
ScaleEntity or2,.03,.03,.03
ScaleEntity or3,.03,.03,.03
ScaleEntity or4,.03,.03,.03

;EntityColor or1,10,10,10
;EntityColor or2,10,10,10
;EntityColor or3,10,10,10
;EntityColor or4,10,10,10
HideEntity or1
HideEntity or2
HideEntity or3
HideEntity or4


;-------------------------sound

sbul=LoadSound("sound\bullet.wav")
SoundVolume sbul,(audS+1)/100

slet=LoadSound("sound\lett.wav")
SoundVolume slet,(audS+1)/100

smov=LoadSound("sound\5.wav")
SoundVolume smov,(audS+1)/100

smov1=LoadSound("sound\men.wav")
SoundVolume smov1,(audS+1)/100

smov2=LoadSound("sound\6.wav")
SoundVolume smov2,(audS+1)/100

stel1=LoadSound("sound\7.wav")
SoundVolume stel1,(audS+1)/100

stel2=LoadSound("sound\8.wav")
SoundVolume stel2,(audS+1)/100
;---------------------------
For o.objekt=Each objekt
 If o\kind$="light"
   LightColor o\en,0,0,0
    ;Stop
  EndIf
Next
;-----------------------------------
ligmen=CreateLight()
RotateEntity ligmen,EntityPitch(cam)-80,EntityYaw(cam),0
ligmen2=CreateLight()
RotateEntity ligmen2,EntityPitch(cam),EntityYaw(cam),0
;------------------------------------------------
CameraZoom cam,2
;----------------------------
helpMesh=LoadMesh("menu\help.b3d")
ScaleMesh helpMesh,.0025,.0025,.0025
TextHelp=CreateTexture(128,64)
 spriteH=CreateSprite()
 EntityOrder spriteh,-1
 ScaleSprite spriteh,.15,.04
 SpriteViewMode spriteh,2
 EntityBlend spriteh,3
 EntityTexture spriteh,TextHelp
  EntityAlpha spriteh,.9
 EntityParent spriteh,helpMesh
PositionEntity helpMesh,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity helpMesh,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity helpMesh,0,0,1.6

;----------------------------
zv#=10

;---------------------------
mo2=LoadAnimMesh("menu\Engine2.b3d")
For i=1 To CountChildren(mo2)
 child=GetChild(mo2,i)
  EntityType child,1
Next
Hose=FindChild(mo2,"Hose")
ScaleEntity mo2,.016,.016,.016
PositionEntity mo2,1,3.5,10.15
RotateEntity mo2,0,180,0
Animate mo2,1,.4
Animate Hose,1,.4


;
fon=LoadAnimMesh("menu\back\Background.b3d")
anim=FindChild(fon,"Background")
anim2=FindChild(fon,"Pipes")
Animate fon,1,.1

For i=1 To 3
CreateRem()
Next 

For i=1 To CountChildren(fon)
 child=GetChild(fon,i)
EntityType child,1
EntityPickMode child, 2
NameEntity child,"fon"
Next
ScaleEntity fon,.3,.3,.3
;------------------------
 PositionEntity fon,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity fon,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity fon,0,0,zv
TurnEntity fon,90,0,180
EntityParent mo2,fon
;--------------------------;-----------------------------
korpsP=CreatePivot()
korpsPa=CreatePivot()
korps=LoadAnimMesh("menu\kolo.b3d")
ScaleEntity korps,.006,.006,.006
m1=LoadMesh("menu\m1.b3d")
ScaleEntity m1,.015,.015,.015
m2=LoadMesh("menu\m2.b3d")
ScaleEntity m2,.015,.015,.015

sh=FindChild(korps,"sh")
;--------------------------------------
PositionEntity korps,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity korps,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity korps,0,-1,6.6
PositionEntity m1,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity m1,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)

PositionEntity m2,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity m2,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)



MoveEntity m1,-.3,4,6.6
MoveEntity m2,.3,4,6.6

m3=CopyEntity(m1)
m4=CopyEntity(m2)
m5=CopyEntity(m1)
m6=CopyEntity(m2)
m7=CopyEntity(m1)
m8=CopyEntity(m2)

PositionEntity m3,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity m3,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
PositionEntity m4,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity m4,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)

MoveEntity m3,-.3,4,6.6
MoveEntity m4,.3,4,6.6




pm1=604
pm2=604
pm3=604
pm4=604
;-----------------------------
EntityParent korps,korpsPa

xx#=-1.7
zv#=3.6

trub=CreateCyl(1)
ScaleEntity trub,.08,2,.08 
PositionEntity trub,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity trub,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity trub,xx,0,3.6
EntityParent trub,korpsp

trubt=CreateCyl(1)
ScaleEntity trubt,.08,.6,.08
PositionEntity trubt,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity trubt,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity trubt,0,2.2,3.6

EntityParent trubt,korpsp
;-------------------------------
;-----------------------------

all=CreateKn("Free for all",3,"all",25)
PositionEntity all,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity all,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity all,0,1.91,3.6
EntityParent all,korpsp
com=CreateKnl("   Team",3,"com",26)
PositionEntity com,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity com,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity com,0,1.9,3.6
EntityParent com,korpsp

red=CreateKn("     Red",3,"red",27)
PositionEntity red,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity red,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity red,0,1.66,3.6
EntityParent red,korpsp

blue=CreateKnl("     Blue",3,"blue",28)
PositionEntity blue,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity blue,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity blue,0,1.65,3.6
EntityParent blue,korpsp


kkn=CreateKnL(">>>",3,"nexs",20)
  ScaleEntity kkn,.02,.025,.02
   ;ScaleEntity kkn,.004,.005,.004
  PositionEntity kkn,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity kkn,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity kkn,-1.7,1.3,3.6
;EntityParent kkn,korpsp
kkn=CreateKnL("<<<",3,"nexd",21)
   ScaleEntity kkn,.02,.025,.02
  ;ScaleEntity kkn,.004,.005,.004
  PositionEntity kkn,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity kkn,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity kkn,-1.7,-1,3.6
tekAi=1
;''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

xx#=1.7
zv#=3.6
;---------------level------------------
levK=LoadAnimMesh("menu\level.b3d")
ScaleEntity levK,.04,.04,.04
rot1=FindChild(levK,"c1")
rot2=FindChild(levK,"c2")
rot3=FindChild(levK,"c3")
motor=FindChild(levK,"Rotor")
reik=FindChild(levK,"reik")
llc=FindChild(levK,"korp")
;NameEntity llc,"fon"
EntityPickMode llc,2,1
;EntityColor llc,150,150,150


levpc=LoadAnimMesh("menu\levP.b3d")
ScaleEntity levpc,.02,.02,.02
ll=FindChild(levpc,"corp")
screen=FindChild(levpc,"screen")
EntityPickMode screen,2,1
NameEntity screen,"fon"


PositionEntity levpc,-1,-.3,-.8

levp=CreatePivot()
PositionEntity levp,-.3,.12,-.27

EntityParent levpc,levp
EntityParent levp,reik

PositionEntity levK,0,607,7
Ylev=607
movLev=smec
movLevb=smec
;---------------------------------------
truba=CreateCyl(1)
ScaleEntity truba,.08,2,.08 
PositionEntity truba,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity truba,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity truba,xx,0,zv
;-----------------------------------
men$="glavn"
 If game=0 
  go=CreateKn("    Go !",1,"go",1)
  PositionEntity go,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity go,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity go,xx,1.2,zv
Else
 eg=CreateKn("  End round",1,"eg",35)
  PositionEntity eg,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity eg,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity eg,xx,1.2,zv

EndIf
If game=0
 options=CreateKn("  Options",1,"options",2)
 PositionEntity options,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity options,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity options,xx,.87,zv
Else
  ret=CreateKn("  Return",1,"return",36)
 PositionEntity  ret,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity  ret,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity  ret,xx,.87,zv
EndIf

 Tan=CreateKn("   Setup",1,"setup",3)
 PositionEntity Tan,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity Tan,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity Tan,xx,.54,zv

ed=CreateKn("  Profile",1,"edit",4)
 PositionEntity ed,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity ed,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity ed,xx,.21,zv


lev=CreateKn("    Level",1,"level",5)
 PositionEntity lev,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity lev,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity lev,xx,-.12,zv

  cred=CreateKn("  Credits",1,"credit",6)
  PositionEntity cred,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity cred,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity cred,xx,-.45,zv

  quit=CreateKn("    Quit",1,"quit",7)
  PositionEntity quit,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity quit,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity quit,xx,-.8,zv
;--------options-----------------
video=CreateKn("    Video",2,"video",8)
  PositionEntity video,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity video,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity video,xx,1.2,zv
audio=CreateKn("    Audio",2,"audio",9)
  PositionEntity audio,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity audio,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity audio,xx,.7,zv
control=CreateKn("   Effects",2,"effect",10)
  PositionEntity control,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity control,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity control,xx,.2,zv
cancel=CreateKn("  Cancel",2,"cansel",11)
  PositionEntity cancel,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity cancel,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity cancel,xx,-1,zv
;----------quit------------------
yes=CreateKn("     Yes",5,"yes",37)
  PositionEntity yes,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity yes,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity yes,xx,.2,zv
no=CreateKn("     No",5,"no",38)
  PositionEntity no,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity no,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity no,xx,-.3,zv
;-------------video--------------
vid1=CreateKn("1280?1024",6,"1280?1024",12)
  PositionEntity vid1,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity vid1,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity vid1,xx,1.2,zv

vid1=CreateKn("1024x768",6,"1024x768",12)
  PositionEntity vid1,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity vid1,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity vid1,xx,.87,zv

vid2=CreateKn("  800x600",6,"800x600",12)
  PositionEntity vid2,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity vid2,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity vid2,xx,.55,zv

vid3=CreateKn("  640x480",6,"640x480",12)
  PositionEntity vid3,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity vid3,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity vid3,xx,.2,zv

aplly=CreateKn("   Aplly",6,"aplly",39)
  PositionEntity aplly,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity aplly,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity aplly,xx,-.3,zv
;----------------audio---------------------
adds=CreateKn("       +",7,"add",14)
  PositionEntity adds,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity adds,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity adds,xx,.7,zv

aud=CreateKn("    "+audS,7,"aud",13)
  PositionEntity aud,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity aud,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity aud,xx,.2,zv

decs=CreateKn("       -",7,"dec",14)
  PositionEntity decs,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity decs,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity decs,xx,-.3,zv

;----------------effect---------------------
low=CreateKn("    Low",8,"low",15)
  PositionEntity low,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity low,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity low,xx,.7,zv

med=CreateKn("  Medium",8,"med",16)
  PositionEntity med,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity med,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity med,xx,.2,zv

hig=CreateKn("    High",8,"hig",17)
  PositionEntity hig,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity hig,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity hig,xx,-.3,zv
;---------------------setup----------------------
SetFont fon5a
createKnN("  Delete",3,"delbot","del",24)
createKnN("   Prev",3,"prev","prev",23)
createKnN("   Next",3,"next","next",22)

addbot=CreateKnL("  Player",10,"player",34)
PositionEntity addbot,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity addbot,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity addbot,-1.7,.95,3.6

;--------------------level------------------------

lev=CreateKn("Previous",9,"prevL",32)
  PositionEntity lev,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity lev,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity lev,xx,-.2,zv
dlev=CreateKn("  Next",9,"nextL",33)
  PositionEntity dlev,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity dlev,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity dlev,xx,-.5,zv
;----------------profile-----------------------------
delp=CreateKn("  Delete",13,"delp",30)
  PositionEntity delp,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity delp,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity delp,xx,-.5,zv
newp=CreateKn("  New",13,"newp",31)
  PositionEntity newp,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity newp,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity newp,xx,-.75,zv

profPan=LoadMesh("menu\profile.b3d")
ScaleEntity profPan,.025,.025,.025

 TextureProf=CreateTexture(256,64)
 spritef=CreateSprite()
 EntityOrder spritef,-1
 ScaleSprite spritef,.6,.1
 SpriteViewMode spritef,2
 EntityBlend spritef,3
 EntityTexture spritef,TextureProf
PositionEntity spritef,-.06,-.28,0
EntityColor spritef,180,180,180
EntityParent spritef,profPan

PositionEntity profPan,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity profPan,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity profPan,-.5,.5,zv

 

trubaf=CreateCyl(1)
ScaleEntity trubaf,.08,2,.08
 
PositionEntity trubaf,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity trubaf,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity trubaf,-1.7,.5,zv
TurnEntity trubaf,0,0,90
EntityParent profPan,trubaf
MoveEntity trubaf,0,3,0
;----------------------------------------------------
SetupRobot()


PositionEntity korpsP,0,4,0
PositionEntity korpsPa,0,-5,0
;===============script
If game=0  
  LoadCfg()
  profile()

 LoadScriptSM()

ChangeDir Gdir$
EndIf

kolAi=0
For a.ai=Each ai
 kolAi=kolAi+1
Next
;-----------end------------------

 menKn(1)
PererisScript()
End Function
;============================
Function Menu()
xfon#=EntityX(fon)
yfon#=EntityY(fon)
zfon#=EntityZ(fon)
 Repeat
	Repeat
	elapsed = MilliSecs () - Time
	Until elapsed	
	ticks = elapsed / period	
	tween# = Float (elapsed Mod period) / Float (period)	
For framelimit = 1 To ticks
	If framelimit = ticks CaptureWorld 
	Time = Time + period
   UpdateWorld
;------------------------
mox#=MouseX()
moy#=MouseY()
If timeKn>0 timeKn=timeKn-1
;-------------------------------
CameraProject cam,EntityX(pricm,1),EntityY(pricm,1),EntityZ(pricm,1)
xpr#=(mox-ProjectedX())/100
ypr#=-(moy-ProjectedY())/100
MoveEntity pricm,xpr,ypr,0
;-------------------------
If men$="setup"
If Ykor>-.5
  Ykor=Ykor-.1
EndIf
 updateMod()
Else
  If Ykor<4 
   Ykor=Ykor+.1 
 EndIf
EndIf
PositionEntity korpsP,0,Ykor,0
PositionEntity korpsPa,0,-Ykor-1,0

If men$="level"
  If Ylev>602.15 
   Ylev=Ylev-.1
   If ChannelPlaying (sHmov)=0  sHmov=PlaySound(smov)
  EndIf
 updateLev()
Else
  If Ylev<607 
     Ylev=Ylev+.1
     If ChannelPlaying (sHmov)=0  sHmov=PlaySound(smov)
  EndIf 
EndIf

PositionEntity levK,0,Ylev,7

If KeyHit(1)
If men$="glavn" And game<>0
  mexit=10
EndIf
EndIf
;----------------------------
Picked=CameraPick(cam, mox, moy)

If MouseHit(1)
     
If Picked<>0
 If picked=screen
      RlevP=-5
  Else
        RlevP=2
 EndIf
If EntityName(picked)="fon"

   effect(Picked)
Else
   effectK(Picked)
    
  If timeKn=0 And mexit=0 Kn(Picked)         
EndIf 
Picked=0 
  dyfon#=.05
  MoveMouse mox,moy-20 
   FlushMouse
EndIf
Else
   If Picked<>0
    If helpme=1  help(Picked)
    EndIf
End If

UpdateFire()
UpdateOsk()
UpdateFlash()
updateSprite()
UpdateKn()
UpdateRem()
UpdateBlur()
UpdateHelp()

If ChannelPlaying (chanM)=0 
   chanM=PlayMusic ("sound\music\le.ogg")
  ChannelVolume chanM,(audS+1)/100
EndIf
;-------------------------
If mexit>1 mexit=mexit-1
Next
RenderWorld
;DrawImage kur,mox-32,moy-32
;-------------------
;yyy=100
;Color 255,255,0
;Text 200,200,tekAi
;Text 100,10,TrisRendered()
;For k.knop=Each knop
;If k\tip=10 Or k\tip=11
  ;If k\tip=10 
 ;  Text 300,yyy,k\men$
; Text 480,yyy,EntityY(k\pivot)
; Else
;Text 20,yyy,k\men$
; Text 160,yyy,EntityY(k\pivot)


; EndIf
 ; yyy=yyy+40
;EndIf
;Next
;Color 255,255,255	
;x_x = 100
;SetFont fon1
;For t.tank=Each tank
;Text 280,yyy,t\Color$
;yyy=yyy+40
;Next

;frames=frames+1
;If MilliSecs()-render_time=>1000  Then afps=frames : frames=0 : render_time=MilliSecs()
;Color 0,255,0
;SetFont fon4
;Text 900*fx,20*fx,"FPS   "+afps
If KeyHit(88) skrinshot()
Flip 0
Until mexit=1 Or game=10
mexit=0
;If  men$="go" game=30
If men$="yes"
       FreeMenu() 
       EndGame()
       ;ShowWindow SystemProperty("AppHWnd"),0
       End
EndIf
End Function
;=======================
Function FreeMenu()
If fpsSpeed>1 
  fps=50*fpsSpeed
  period = 1000 / FPS
EndIf
;-------------------------
For o.objekt=Each objekt
 If o\kind$="light"
   LightColor o\en,-o\r*2.5,-o\g*2.5,-o\b*2.5
  EndIf
Next
For f.fire=Each fire
FreeEntity f\en
Delete f
Next
For os.osk=Each osk
FreeEntity os\en
Delete os
Next

For b.Flash=Each Flash
FreeEntity b\en
Delete b
Next
For s.part=Each part
FreeEntity s\en
Delete s
Next

For k.knop=Each knop
FreeEntity k\en
FreeEntity k\pivot
FreeTexture k\texture
Delete k
Next
For r.rem=Each rem
FreeEntity r\en
Delete r
Next


;-----------------------------------
FreeEntity ligmen
FreeEntity ligmen2
;-------------------------
fov_# = 65.47
;fov_# = 90
zoom#=(1.0/Tan(fov_#/2.0))
CameraZoom cam,1.4
CameraRange cam,.5,6000 
FreeImage kur
;FreeImage sledm
;-------------sound----
FreeSound sbul
FreeSound slet
FreeSound smov
FreeSound smov1
FreeSound smov2
FreeSound stel1
FreeSound stel2
;------------------------
FreeTexture TextHelp
FreeTexture TextureProf
;-------------------------

FreeEntity knp
FreeEntity knl
FreeEntity fon
FreeEntity truba
FreeEntity helpMesh

;----------------
FreeEntity korpsP
FreeEntity korpsPa

FreeEntity trub
FreeEntity trubt
FreeEntity m1
FreeEntity m2
FreeEntity m3
FreeEntity m4
FreeEntity m5
FreeEntity m6
FreeEntity m7
FreeEntity m8
;----------------
FreeEntity levK
FreeEntity levpc
;-----------------
FreeEntity profPan
FreeEntity trubaf
;----------------
If game<>0
EntityParent pric,0
PositionEntity pric,EntityX(cam,1),EntityY(cam,1),EntityZ(cam,1)
RotateEntity pric,EntityPitch(cam,1),EntityYaw(cam,1),EntityRoll(cam,1)
MoveEntity pric,0,0,8
EntityParent pric,cam
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
;Stop
EndIf
End Function
;========================
Function oskmenu(ent,kol)
For i=1 To kol
    xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
   xr#=EntityPitch(ent)
   yr#=EntityYaw(ent)
   zr#=EntityRoll(ent)
o.osk=New osk
o\en=CreatePivot() 
o\mesh=CopyEntity(osk)
ShowEntity o\mesh

ssk#=Rnd(.05,.1)
ScaleEntity o\mesh,ssk,ssk,ssk

EntityParent o\mesh,o\en
PositionEntity o\en,xv,yv,zv
PointEntity o\en,cam
MoveEntity o\en,0,0,.2
TurnEntity  o\en,0,0,Rand(0,360)
TurnEntity  o\en,Rand(60,90),0,0

o\sk=Rnd(.1,.7)
o\gr=0
o\dgr=-.01
o\live=100
o\sl1=Rand(2,20)
o\sl2=Rand(2,20)
o\tip=1
Next
End Function

Function sparcmenu(ent,ss#,fs)
    xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
 

f.fire=New fire
f\en=CopyEntity(Sparks)

PositionEntity f\en,xv,yv,zv
PointEntity f\en,cam
MoveEntity f\en,0,0,.4

TurnEntity  f\en,0,0,Rand(0,180*fs)
TurnEntity  f\en,Rand(70,80),0,0

f\sk=ss*10
MoveEntity f\en,0,0,-2*f\sk
f\gr=0
f\dgr=0
f\live=100

f\sl1=ss
f\sl2=ss
f\tip=0
ScaleSprite f\en,ss,ss

End Function
;==================
Function smokMenu(ent)
xv#=EntityX(ent)
 yv#=EntityY(ent)
 zv#=EntityZ(ent)
For i=1 To 5

s.part=New part
s\en=CopyEntity(Smoke)

PositionEntity s\en,xv+Rnd(-.1,.1),yv+Rnd(.1,.1),zv+Rnd(-.1,.1)

RotateEntity s\en,0,Rand(0,360),0
TurnEntity s\en,Rand(-90,0),0,0

RotateSprite s\en,Rand(0,360)
s\cv=Rand(200,250)
EntityColor s\en,s\cv,s\cv,s\cv
;EntityBlend s\en,3

s\sc=1
s\dsc#=20
s\al=.4
s\dal=-.01
s\gr=.03

s\tip=1
;s\skr=7
;s\dskr=-.8
ScaleSprite s\en,s\sc,s\sc
Next
End Function
;===========================
Function effect(Picked)
;SoundVolume sbul,audS/100
PlaySound sbul

PositionEntity pivot,PickedX(),PickedY(),PickedZ()    
b.Flash=New Flash
b\en=CopyEntity(Sparks)
EntityOrder b\en,-1
PositionEntity b\en,PickedX(),PickedY(),PickedZ()
b\sc=.5
b\dsc=.4
b\psc=1
b\ssc=-1
smokMenu( pivot)
;TranslateEntity pivot,0,0,-4
oskmenu(pivot,10)
fs=Rand(-1,1)
If fs=0 fs=1
     For i=1 To 10
        ss#=Rnd(.06,.1)       
     sparcmenu(pivot,ss,fs)
 Next

End Function

Function effectK(Picked)
 PlaySound sbul
     PositionEntity pivot,PickedX(),PickedY(),PickedZ()
       fs=Rand(-1,1)
           If fs=0 fs=1
     For i=1 To 20
       ss#=Rnd(.01,.02)     
     sparcmenu(pivot,ss,fs)
     Next
b.Flash=New Flash
b\en=CopyEntity(Sparks)
EntityOrder b\en,-1
PositionEntity b\en,PickedX(),PickedY(),PickedZ()
b\sc=.5
b\dsc=.4
b\psc=1
b\ssc=-1
;smokMenu( pivot)

End Function
;========================
Function CreateKnL(n$,tip,ggg$,h)
ClsColor 0,0,0

k.knop=New knop
    k\name$=n$
   k\tip=tip
   k\men$=ggg$
   k\h=h
k\en=CopyEntity(knl)
NameT$=Handle(k)
NameEntity k\en,NameT$

ScaleEntity k\en,.025,.025,.025
;ScaleEntity k\en,.005,.005,.005

EntityPickMode k\en,2,False
k\pivot=CreatePivot()

SetFont fon6
k\enP=spriteKn(k.knop,n$)

;ScaleEntity k\enP,.006,.006,.05
PositionEntity k\enP,.47,-.12,-.01
EntityParent k\enP,k\en
k\Text=1
k\spt=Rand(5,10)

EntityColor k\enP,180,180,180
   Return k\en
End Function
;=================
Function CreateKn(n$,tip,ggg$,h)
ClsColor 0,0,0

k.knop=New knop
    k\name$=n$
   k\tip=tip
   k\men$=ggg$
   k\h=h
k\en=CopyEntity(knp)
NameT$=Handle(k)
NameEntity k\en,NameT$

ScaleEntity k\en,.025,.025,.025
;ScaleEntity k\en,.005,.005,.005
EntityPickMode k\en,2,False
k\pivot=CreatePivot()

SetFont fon6
k\enP=spriteKn(k.knop,n$)
;ScaleEntity k\enP,.006,.006,.03
PositionEntity k\enP,-.47,-.12,-.01
EntityParent k\enP,k\en
k\spt=Rand(5,10)
EntityColor k\enP,180,180,180
Return k\en
End Function
;=================
Function createKnN(n$,tip,ggg$,chil$,h)
k.knop=New knop
k\h=h
k\name$=n$
k\tip=tip
k\men$=ggg$ 
k\en=FindChild(korps,chil$)
NameT$=Handle(k)
NameEntity k\en,NameT$
EntityPickMode k\en,2,False
FitMesh k\en,-MeshWidth(k\en)/2,-MeshHeight(k\en)/2,-MeshDepth(k\en)/2,MeshWidth(k\en),MeshHeight(k\en),MeshDepth(k\en)
;tkna=LoadTexture("menu\6M25.JPG",64)

k\enP=spriteKn(k.knop,n$)
ScaleSprite k\enP,.6,.2
PositionEntity k\enP,EntityX(k\en,1)+.22,EntityY(k\en,1) ,EntityZ(k\en,1)
MoveEntity k\enP,0,0,-.1
EntityParent k\enP,k\en
k\pivot=CreatePivot()
k\Text=2
k\sl3=EntityZ(k\en)
EntityColor k\enP,180,180,180
End Function
;========================
Function CreateCyl(tip)


cyl=CreateCylinder()
EntityPickMode cyl, 2
NameEntity cyl,"knop"
Select tip
 Case 1
   ctex=LoadBrush("texture\M25.JPG",64)
 PaintMesh cyl,ctex
 FreeBrush ctex
 Case 2
   ;ctex=LoadBrush("texture\4.JPG")
      ctex=LoadBrush("texture\M2.JPG",64)
 PaintMesh cyl,ctex
 FreeBrush ctex
 Case 3 
   
End Select 
 
 Return cyl
End Function
;======================
Function UpdateKn()
 For k.knop=Each knop
 If  k\Text=2
    PositionEntity k\en,EntityX(k\en),EntityY(k\en),k\sl3+k\sl1/3
    k\sl1=k\sl1/1.1
  Else
      k\sl3=-(EntityYaw(k\en)-EntityYaw(k\pivot))/60     
      k\sl1=k\sl1+k\sl3+Sin(Time/k\spt)/30
      k\sl1=k\sl1/1.08  
      TurnEntity  k\en,0,k\sl1,0
   If Abs(EntityYaw(k\en))>99
      HideEntity k\en
      HideEntity  k\enP
   EndIf
   If Abs(EntityYaw(k\en))<40
      ShowEntity k\en
      ShowEntity k\enP
   EndIf
 EndIf
 Next
End Function
;=========================
Function Kn(ent) 
ti=EntityName(ent)
k.knop=Object.knop(ti)
If k<>Null
 timeKn=20
     If k\Text=0
         k\sl1=k\sl1-15
     Else
         k\sl1=k\sl1+15
     EndIf
       zavKn(k.knop) 
Else
   d.rem=Object.rem(ti) 
    If d<>Null
      EntityRadius d\en,0
         Animate d\en,0
         oskR(d\en)
         effect(d\en)
         HideEntity d\en
         
       d\tip=3
      d\sl6=200
    EndIf
EndIf
 ;Next
End Function
;=======================
Function zavKn(k.knop)

If k\men$="cansel" Or (k\men$="no" And mexit=0)
                 If  men$="setup" saveProfile():PlaySound smov1
                 If k\men$="cansel"  bwig=wig:bhei=hei

      If men$="options" Or men$="setup" Or men$="credit" Or men$="quit" Or  men$="level" Or  men$="edit"
         If rotModb=0 men$="glavn"
      Else
         men$=bmen$
      EndIf
Else 
    If  men$<>"setup" And men$<>"level" And men$<>"edit" 
      men$=k\men$ 
      EndIf 
     If men$="options" Or  men$="setup" Or  men$="credit" Or  men$="quit" Or  men$="level" Or  men$="edit" 
       bmen$=men$  
     EndIf

          
EndIf
;-----------------------------------
If k\men$=men$ Or  men$="glavn" Or men$="options"
   Select men$
       Case  "glavn"
             menKn(1)
       Case "go"
          If mexit=0 mexit=50
               timeTur=timeTurM
       Case "eg" 
             If mexit=0 mexit=50
               game=20
       Case "options"
            menKn(2)
       Case "return"
            mexit=50
       Case "level"
           If game=0 
              menKn(9)
           Else
             men$= "glavn"
           EndIf
       Case "setup"
          If game=0
                    PlaySound smov1
              menKn(3)
             If TekBot$=""
                 t.tank=First tank
                 TekBot$=t\name$
                 tekModel(TekBot$)   
             EndIf
             PererisScript()
          Else
             men$= "glavn"
          EndIf
       Case "edit"
                If game=0
               menKn(13)
               PererisScript()
                 Else
             men$= "glavn"
          EndIf
       Case "credit"
           menKn(4)
       Case "quit"
            menKn(5)
        Case "video"
              menKn(6)
        Case "audio"
             menKn(7)
        Case "effect"
              menKn(8)
       Case "yes"
            ;menKn(9)
                  save()
           If mexit=0 mexit=30
           Case "1280?1024"
             bwig=1280
             bhei=1024
            menKn(6)

       Case "1024x768"
             bwig=1024
             bhei=768
            menKn(6)
                   ;men$=bmen$
       Case "800x600"
            bwig=800
            bhei=600
            menKn(6)
                 ; men$=bmen$
       Case "640x480"
            bwig=640
            bhei=480
            menKn(6)
       Case "aplly"
         If  bwig<>wig
            wig=bwig
            hei=bhei
            game=10
                   save()
          EndIf 
       Case "add"
           If auds<100
              audS=audS+10
              aud()
                     save()
           EndIf
       Case "dec"
            If auds>0
              audS=audS-10
              aud()
                      save()
            EndIf
          Case "low"
           effect=1
            menKn(8)
                  save() 
          Case "med"
           effect=2
            menKn(8)
                  save()
          Case "hig"
           effect=3
            menKn(8)  
              save()
    End Select
 EndIf
;----------level-----------------
If k\tip=9 And movLevb=movLev
   If k\men$="prevL"
     
      movLevb=-50
         Mlev=2
   EndIf
   
   If k\men$="nextL"
       movLevb=-50
          Mlev=1
  EndIf

EndIf
;-----------profile-----------------
If k\tip=13
  If k\men$<>profile$ And k\men$<>"delp" And k\men$<>"newp"
     profile$=k\men$
     deletetankAll() 
     loadProfile()
     ;addscript()
     command()
     menKn(13)
        PererisScript()
   Else
      If k\men$="delp"
         delProfile()
         deletetankAll()
         loadProfile()
         ;addscript()
         command()
        menKn(13)
         PererisScript() 
         scrollProfile()
     EndIf
       If k\men$="newp"
           If kolprofile<7
          deletetankAll()
          NewProfile()
          commandColor()
          saveProfile()
          scrollProfile()
          menKn(13)
         PererisScript()
         EndIf
       EndIf
  EndIf
   t.tank=First tank
   TekBot$=t\name$
   tekModel(TekBot$)
EndIf
;--------setup---------------
If (k\tip=3 Or k\tip=10 Or k\tip=11 Or k\tip=12) And rotModb=rotMod
;--------------------------------------
;If k\men$=mg$
  
  If k\tip=10
     kolbot()
     If kolbot<8 
          mg$=""
          addbot(k\name$)
          kolbot()
          knscroll()
      PererisScript()
          
     EndIf
     ;-----------------
     ;tekModel(TekBot$)    
  EndIf      

;---------------------------------
If k\men$="nexd" And kolAi>8*tekAi  
  tekAi=tekAi+1
  KnAi()
  PererisScript()
   menKn(3)
EndIf

If k\men$="nexs" And tekAi>1  
  tekAi=tekAi-1
  KnAi()
  PererisScript()
   menKn(3)
EndIf
;-----------------------------

If k\tip=12
  
       TekBot$=k\men$
       PererisScript()
      tekModel(TekBot$)
 
Else

If command=1
 If k\men$="red" Or k\men$="blue"
    ColorB(k\men$)      
     PererisScript()
     ;mg$=""
   EndIf
EndIf
;--------------------------------
     If k\men$="prev"   
        For t.tank=Each tank
          If TekBot$=t\name$
                For r.robot=Each robot 
                If  r\name$= t\m$
            r= Before r
                  If r=Null
                    r.robot =Last robot   
                  EndIf 
                  t\m$=  r\name$
                  Goto fba
                EndIf 
                Next
          EndIf   
        Next
            .fba
             ;Stop   
        tekModel(TekBot$)
         mg$=""
     EndIf
;-------------------------------------
     If k\men$="next"
      For t.tank=Each tank
          If TekBot$=t\name$ 
               For r.robot=Each robot 
                  If  r\name$= t\m$  
             r = After r
             If r=Null
               r.robot = First robot
             EndIf 
                    t\m$=r\name$
                   Goto fb
                 EndIf 
                 Next
          EndIf   
        Next 
           .fb  
       tekModel(TekBot$)
       mg$=""
   EndIf
;----------------------------------
 If k\men$="all" 
         If command=1
             command=0  
        PererisScript()   
         mg$=""
     EndIf
EndIf

 If k\men$="com" 
            If command=0
         command=1  
        PererisScript()
         mg$=""
     EndIf
 EndIf
   
;------------------------
  If k\men$="delbot"
    If TekBot$=namePl$ player=0
    If kolbot>1
     deletetank(TekBot$)
     kolbot()
    delKn(TekBot$)
    knscroll()
    t.tank=First tank
      TekBot$=t\name$
      PererisScript()
     tekModel(TekBot$)
     command()
     PererisScript()
    EndIf
    EndIf
EndIf


;------------------------------
EndIf
;-----------------------------

End Function

;======================

Function menKn(tip)
   For k.knop=Each knop
If k\tip=tip Or (tip=3 And (k\tip=10 Or k\tip=12) )
k\sl2=0
;-----------video----------
If k\tip=6 Or k\tip=8
    
If  (k\men$="1280?1024"And bwig=1280) Or(k\men$="1024x768"And bwig=1024) Or(bwig=800 And k\men$="800x600")Or(bwig=640 And k\men$="640x480")Or (effect=1 And k\men$="low")Or (effect=2 And k\men$="med")Or (effect=3 And k\men$="hig")
  
      EntityColor  k\enP,255,150,0 
Else
    EntityColor  k\enP,100,100,100  
    If k\men$="aplly" 
       If bwig=wig 
       EntityColor  k\enP,10,10,10 
        Else
         EntityColor  k\enP,100,100,100
       EndIf
    EndIf
EndIf

EndIf
;-------end video--------
Else
   k\sl2=100
      
EndIf

;-----------------------------------------
If tip<>1 And tip<>5 And k\men$="cansel" 
   k\sl2=0
EndIf
RotateEntity k\pivot,0,k\sl2,0
  
Next

End Function
;=============================
Function aud()
For k.knop=Each knop
If k\men$="aud"
  k\name$="      "+audS
   SetFont fon6
enP=spriteKn(k.knop,k\name$)
;ScaleEntity enP,.006,.006,.03
PositionEntity enP,EntityX(k\enP,1),EntityY(k\enP,1),EntityZ(k\enP,1)
RotateEntity enP,EntityPitch(k\enP,1),EntityYaw(k\enP,1),EntityRoll(k\enP,1)
EntityColor enP,180,180,180
   FreeEntity k\enP
    k\enP= enP
EntityParent k\enP,k\en
SoundVolume sbul,(audS+1)/100
SoundVolume slet,(audS+1)/100
SoundVolume smov,(audS+1)/100
SoundVolume smov1,(audS+1)/100 
SoundVolume smov2,(audS+1)/100 
SoundVolume stel1,(audS+1)/100
SoundVolume stel2,(audS+1)/100

ChannelVolume chanM,(audS+1)/100
 
EndIf
Next
End Function
;=================
Function LoadScriptSM()

ChangeDir Gdir$
DeleteAllScripts()
;------------------------------
kolbot=0
dir=ReadDir("scripts\")
ChangeDir "scripts\"
Repeat
File$=NextFile$(dir)
If File$="" Exit
If File$<>"." And File$<>".."
If Right(File$,3)=".ai"
  kolbot=kolbot+1
 name$=Left(File$,Len(File$)-3)
;-------------createtank---------------------------------
ChangeDir Gdir$

 
createAI(File$,name$,kolbot)

EndIf
EndIf
Forever
CloseDir dir
loadProfile()
End Function
;=======================
Function viewScript()
  fff=100
  For sc.script=Each script
    Text 200,fff,sc\name$
    fff=fff+20
  Next
End Function
;====================
Function TypePick(ent1,ent2)
If ent1<>ent2
nam1$=EntityName(ent1)
nam2$=EntityName(ent2)

Select nam1$
  ;Case "bonus"
    ;Return 3
 Case "building"
    Return 3
 Case "wall"
    Return 4
  Case "bul"
    Return 5
   Case "roc"
    Return 6
   Case "rem"
    Return 7
 Case "red","blue"
   If  command=1
   If nam1$=nam2$
     Return 2
   Else
     Return 1
   EndIf
   Else
      Return 1
   EndIf
End Select
EndIf
End Function
;================
Function Lives(ent)
 For t.tank=Each tank
 If t\corpus=ent
   Return t\live
 EndIf
 Next
End Function
;=======================
Function PererisScript()

 For k.knop=Each knop 
     If k\tip=13
      If k\men$=profile$
         EntityColor  k\enP,255,180,0  
      Else
         EntityColor k\enP,180,180,180
      EndIf
        If k\men$="newp"
          If kolprofile<7
              EntityColor k\enP,180,180,180 
           Else
             EntityColor  k\enP,40,40,40
           EndIf
        EndIf
    EndIf
;-------------
   If k\men$="level" Or k\men$="setup" Or k\men$="edit"
      If  game=0
       EntityColor k\enP,180,180,180
     Else
        EntityColor  k\enP,40,40,40
     EndIf
   EndIf
  If k\men$="nexd"
  If kolAi>8*tekAi 
        EntityColor k\enP,180,180,180
   Else
       EntityColor  k\enP,40,40,40
   EndIf
   EndIf
   If k\men$="nexs"
  If tekAi>1
       EntityColor k\enP,180,180,180
   Else
       EntityColor  k\enP,40,40,40
   EndIf
    EndIf
If k\tip=3 Or k\tip=12 
If k\men$<>"delbot" And k\men$<>"prev" And k\men$<>"next" And k\men$<>"nexs"And k\men$<>"nexd"
If command=0
  If k\men$=TekBot$ 
      EntityColor  k\enP,255,180,0
  Else    
     EntityColor k\enP,180,180,180
  EndIf
  If k\men$="all" 
     EntityColor k\enP,255,180,0
  EndIf
   If k\men$="com" 
     EntityColor k\enP,180,180,180
  EndIf
  If k\men$="red" Or k\men$="blue"
    EntityColor k\enP,40,40,40

  EndIf

Else
    
   If k\men$=TekBot$ 
        If k\Color$="red" 
         EntityColor  k\enP,255,100,0
       EndIf 
        If k\Color$="blue"  
          EntityColor  k\enP,0,170,255
         EndIf
    Else
     If k\Color$="red" 
        EntityColor  k\enP,200,20,0
      EndIf 
     If k\Color$="blue"  
        EntityColor  k\enP,0,20,200
      EndIf
  EndIf
    If k\men$="red" Or k\men$="blue"
    EntityColor k\enP,180,180,180
  EndIf
  
  If k\Color$="" 
     EntityColor k\enP,180,180,180
  EndIf 
   If k\men$="all" 
     EntityColor k\enP,180,180,180
  EndIf
   If k\men$="com" 
     EntityColor k\enP,255,180,0
  EndIf  
EndIf
  
EndIf
EndIf

If k\men$="player"
   If player=1 
       EntityColor k\enP,40,40,40
     Else
     EntityColor k\enP,180,180,180
   EndIf
EndIf
Next
End Function
;==================
Function addscript()
kolbot=0
For t.tank=Each tank
kolbot=kolbot+1
If kolbot<8
 kkn=CreateKn(" "+t\name$,12,t\name$,18)
 PositionEntity kkn,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity kkn,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity kkn,1.7,1.17-kolbot*.23,3.6
Else
  deletetank(t\name$)
EndIf


Next

End Function
;================
Function command()
kolbot=0
  For t.tank=Each tank
     kolbot=kolbot+1
  Next

  kolbotb=0
  For t.tank=Each tank
     kolbotb=kolbotb+1
       ;If kolbotb<=kolbot/2
      ;    t\Color$="red"
      ; Else
      ;   t\Color$="blue"
      ; EndIf
      commandKn(t.tank)
  Next
  
End Function
;===============
Function commandColor()
kolbot=0
  For t.tank=Each tank
     kolbot=kolbot+1
  Next

  kolbotb=0
  For t.tank=Each tank
     kolbotb=kolbotb+1
      If kolbotb<=kolbot/2
          t\Color$="red"
       Else
         t\Color$="blue"
       EndIf
      commandKn(t.tank)
  Next
  
End Function

;=============
Function ColorB(colorB$)
  For t.tank=Each tank
       If t\name$=TekBot$
          t\Color$=colorB$
          commandKn(t.tank)
       EndIf     
  Next  
End Function
;======================
Function commandKn(t.tank)
 For k.knop=Each knop
       If k\men$=t\name$
            k\Color$=t\Color$
         EndIf
       Next
End Function

;======================
Function deletetank(name$)
  For t.tank=Each tank
     If t\name$=name$
        Delete t
     EndIf
  Next
End Function

;===================
Function deletetankAll()
  For t.tank=Each tank
          If namePl$<>t\name$ FreeScript(t\sc)
            delKn(t\name$)
        Delete t   
  Next
End Function
;=============
Function kolbot()
 kolbot=0
  For t.tank=Each tank
   kolbot=kolbot+1    
  Next
End Function
;=============
Function delkn(name$)
 For k.knop=Each knop
     If k\men$=name$
        FreeEntity  k\pivot
        FreeEntity  k\en
        ; FreeEntity  k\enp
           ; FreeTexture  k\Text
        Delete k
     EndIf
  Next
End Function

Function knscroll()
kol=0
For k.knop=Each knop
If k\tip=12
;If k\men$<>"addbot" And k\men$<>"delbot" And k\men$<>"prev" And k\men$<>"next"
;If k\Color$<>"" 
kol=kol+1
PositionEntity k\en,EntityX(cam),EntityY(cam) ,EntityZ(cam)
RotateEntity k\en,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity k\en,1.7,1.17-kol*.23,3.6


;EndIf
EndIf
Next
End Function
;===================
Function updateLev()
  If movLevb<movLev  And fl=0
     movlev=movlev-1
     TurnEntity rot1,0,4.5,0
     TurnEntity rot2,0,4.5,0
     TurnEntity rot3,0,5,0
     TurnEntity motor,0,-60,0
        If ChannelPlaying (sHmov)=0  sHmov=PlaySound(smov2)

       If movLevb>=movLev 
            fl=1:movLevb=smec:RlevP=-5
           If Mlev=1 levelImgNext()
           If Mlev=2 levelImgPrev()
         EndIf
  EndIf
  If movLev<smec And fl=1
     movlev=movlev+1
       TurnEntity rot1,0,-4.5,0
     TurnEntity rot2,0,-4.5,0
     TurnEntity rot3,0,-5,0
     TurnEntity motor,0,60,0
       If ChannelPlaying (sHmov)=0  sHmov=PlaySound(smov2)
     If movlev>=smec movlev=smec:fl=0:RlevP=3
  EndIf 
PositionEntity reik,movlev,0,0

EntityParent levp,0

dlevP=-EntityPitch(levp)/60

EntityParent levp,reik

RlevP=RlevP+dlevP

RlevP=RlevP/1.06

TurnEntity levp,RlevP,0,0
End Function
;=====================
Function updateMod()

If rotModb=rotMod
  TurnEntity sh,0,1,0
EndIf

If rotModb<rotMod
    rotModb=rotModb+2
    If rotModb=rotMod rotMod=0:rotModb=0
EndIf

If rotModb>rotMod 
     rotModb=rotModb-2
    If rotModb=rotMod rotMod=0:rotModb=0
EndIf
If rotmodb>170 And rotmodb<190
   eff(korps)
EndIf
If rotmodb=180
  For r.robot=Each robot
     If r\name$=tekMod$
        ShowEntity r\en
      Else
        HideEntity r\en
      EndIf
   Next
EndIf

If rotmodb=0      pm1=604:pms1=0  
If rotmodb=330   pm2=608:pms2=0
If rotmodb=300   pm3=612:pms3=0
If rotmodb=270   pm4=616:pms4=0


y1#=EntityY(m1)
y2#=EntityY(m2)
y3#=EntityY(m3)
y4#=EntityY(m4)
y5#=EntityY(m5)
y6#=EntityY(m6)
y7#=EntityY(m7)
y8#=EntityY(m8)
r1#=pm1-y1
r2#=pm1-y2
r3#=pm2-y3
r4#=pm2-y4
r5#=pm3-y5
r6#=pm3-y6
r7#=pm4-y7
r8#=pm4-y8
If rotmodb>0 And rotmodb<180
   If Abs(r1)<.2
             MoveEntity m1,- (-.1+EntityX(m1))/5,0,0
           ; If Abs(EntityX(m1))<=.4  PlaySound stel2:Stop
     Else      
           blur(m1)
    EndIf  

      If Abs(r2)<.2 
            MoveEntity m2,- (.1+EntityX(m2))/5,0,0      
      Else      
           blur(m1)
    EndIf  

        If Abs(r3)<.2 
          MoveEntity m3,- (-.1+EntityX(m3))/5,0,0
       Else      
           blur(m3)
    EndIf  

      If Abs(r4)<.2 
            MoveEntity m4,- (.1+EntityX(m4))/5,0,0
    Else      
           blur(m4)
    EndIf  

       If Abs(r5)<.2
               MoveEntity m5,- (-.1+EntityX(m5))/5,0,0
     Else      
           blur(m5)
    EndIf  

      If Abs(r6)<.2 
          MoveEntity m6,- (.1+EntityX(m6))/5,0,0
    Else      
           blur(m6)
    EndIf  

     If Abs(r7)<.2 
          MoveEntity m7,- (-.1+EntityX(m7))/5,0,0
     Else      
           blur(m7)
    EndIf  
 If Abs(r8)<.2
        MoveEntity m8,- (.1+EntityX(m8))/5,0,0
   Else 
          blur(m8)
      EndIf
EndIf

If rotmodb>250
   MoveEntity m1,- (.3+EntityX(m1))/5,0,0
   MoveEntity m2,- (-.3+EntityX(m2))/5,0,0   
   MoveEntity m3,- (.3+EntityX(m3))/5,0,0
   MoveEntity m4,- (-.3+EntityX(m4))/5,0,0
   MoveEntity m5,- (.3+EntityX(m5))/5,0,0
   MoveEntity m6,- (-.3+EntityX(m6))/5,0,0
   MoveEntity m7,- (.3+EntityX(m7))/5,0,0
   MoveEntity m8,- (-.3+EntityX(m8))/5,0,0
EndIf
If  rotmodb>180 And rotmodb<250
TurnEntity m1,0,10,0
TurnEntity m2,0,10,0
TurnEntity m3,0,-10,0
TurnEntity m4,0,-10,0
TurnEntity m5,0,10,0
TurnEntity m6,0,10,0
TurnEntity m7,0,-10,0
TurnEntity m8,0,-10,0
 If rotmodb=248
   RotateEntity m1,0,0,0
RotateEntity m2,0,0,0
RotateEntity m3,0,0,0
RotateEntity m4,0,0,0
RotateEntity m5,0,0,0
RotateEntity m6,0,0,0
RotateEntity m7,0,0,0
RotateEntity m8,0,0,0
 EndIf
EndIf
If rotmodb>269 

If EntityY(m1)<pm1
  pms1=pms1+.02
 MoveEntity m1,0,pms1,0
 MoveEntity m2,0,pms1,0
  blur(m1)
  blur(m2)
EndIf

If EntityY(m3)<pm2 
  pms2=pms2+.02
   MoveEntity m3,0,pms2,0
  MoveEntity m4,0,pms2,0
  blur(m3)
  blur(m4)
EndIf
If EntityY(m5)<pm3
  pms3=pms3+.02
  MoveEntity m5,0,pms3,0
 MoveEntity m6,0,pms3,0
  blur(m5)
  blur(m6)
EndIf
If EntityY(m7)<pm4
  pms4=pms4+.02
   MoveEntity m7,0,pms4,0
 MoveEntity m8,0,pms4,0
  blur(m7)
  blur(m8)
EndIf
Else
MoveEntity m1,0,(r1)/8,0
MoveEntity m2,0,(r2)/8,0
MoveEntity m3,0,(r3)/10,0
MoveEntity m4,0,(r4)/10,0
MoveEntity m5,0,(r5)/12,0
MoveEntity m6,0,(r6)/12,0
MoveEntity m7,0,(r7)/14,0
MoveEntity m8,0,(r8)/14,0
EndIf
End Function
;===============
Function SetupRobot()
 dir=ReadDir("Bots\")
;ChangeDir "robot\"
kol=1
;-----------------------------------------
Repeat
	File$=NextFile$(dir)
	If File$="" Exit
	If File$<>"." And File$<>".."
		If Right(File$,3)="b3d"
             r.robot=New robot
             r\en=LoadMesh("Bots\"+File$)
             sc#=1.2/MeshDepth(r\en)
             ScaleEntity r\en,sc,sc,sc
             r\name$=Left(File$,Len(File$)-4)
             xx#=0
             yy#=-.9
             zz#=6.5
             PositionEntity r\en,EntityX(cam),EntityY(cam),EntityZ(cam)
             MoveEntity r\en,xx,yy,zz
             EntityParent r\en,sh

             r\n=kol
             kol=kol+1
		EndIf
	EndIf
Forever
CloseDir dir
ChangeDir Gdir$

End Function
;=================
Function  setupTank()
  
mod1=CreatePivot()
modb1=LoadMesh("Bots\tank1\M1A1.b3d")
PositionEntity modb1,0,6,15
EntityParent modb1,mod1

mod2=CreatePivot()
modb2=LoadMesh("tanks\tank2\T72.b3d")
PositionEntity modb2,0,6,15
EntityParent modb2,mod2

mod3=CreatePivot()
modb3=LoadMesh("tanks\tank3\Challenger.b3d")
PositionEntity modb3,0,6,15
EntityParent modb3,mod3

mod4=CreatePivot()
modb4=LoadMesh("tanks\tank4\Tiger.b3d")
PositionEntity modb4,0,6,15
EntityParent modb4,mod4


ScaleEntity mod1,.012,.012,.012
ScaleEntity mod2,.012,.012,.012
ScaleEntity mod3,.012,.012,.012
ScaleEntity mod4,.012,.012,.012

TurnEntity modb1,5,0,0
TurnEntity modb2,5,0,0
TurnEntity modb3,5,0,0
TurnEntity modb4,5,0,0

xx#=-.17
yy#=-.6
zz#=3.3
PositionEntity mod1,xx,yy,zz
EntityParent mod1,sh1

PositionEntity mod2,xx,yy,zz
EntityParent mod2,sh1
HideEntity mod2
PositionEntity mod3,xx,yy,zz
EntityParent mod3,sh1
HideEntity mod3
PositionEntity mod4,xx,yy,zz
EntityParent mod4,sh1
HideEntity mod4
End Function
;=============
Function  tekModel(name$)
For t.tank=Each tank
 If name$=t\name$
 If tekMod$<>t\m$ 
         rotMod=360
         pm1=600-1.3
         pm2=600-1.1
         pm3=600-.9
         pm4=600-.7
  EndIf
   tekMod$=t\m$
  EndIf   
Next

End Function
;===============
Function LoadMenLevel()
SetFont fon3
put$="level\"
dir=ReadDir(put$)
ChangeDir put$
Repeat
File$=NextFile$(dir)

If File$="" Exit
If File$<>"." And File$<>".." And FileType (File$)=2
maxLevel=Right(File$,2)
  If Left(File$,5)="level"
     lev.lev=New lev
     lev\level$= File$
     plev$=File$+"\image\level.jpg"
     img=LoadTexture(plev$)

  If img=0
    img=CreateTexture(320,320)
    SetBuffer TextureBuffer(img) 
    ClsColor 50,50,50
    Cls
    Color 255,0,0
    Text 170,120,"No Image"
    SetBuffer BackBuffer ()

  EndIf

    
    SetBuffer TextureBuffer(img) 
    Color 0,0,0
    Text 170,20,lev\level$
    Text 172,22,lev\level$
    Text 172,20,lev\level$
    Text 170,22,lev\level$
    Color 255,150,0
    Text 171,21,lev\level$
    SetBuffer BackBuffer ()
 lev\img=CreateBrush(255,255,255) 
 BrushTexture lev\img,img

  EndIf
EndIf

Forever
CloseDir dir

ChangeDir Gdir$
For lev.lev=Each lev
If lev\level$=level$
PaintMesh screen,lev\img
EndIf
Next

ClsColor 0,0,0
SetFont fon2

End Function
;==============
Function levelImgNext()
For lev.lev=Each lev
If lev\level$=level$
  lev=After lev
If lev=Null lev.lev=First lev
  level$=lev\level$
  PaintMesh screen,lev\img
EndIf
Next
End Function
;==============
Function levelImgPrev()
For lev.lev=Each lev
If lev\level$=level$
  lev=Before lev
If lev=Null lev.lev=Last lev
  level$=lev\level$
   PaintMesh screen,lev\img
EndIf
Next
End Function
;=======================
Function save()
;--------------------
wf=WriteFile("cfg\setup.cfg")
;--------------------------------------
WriteLine (wf,"fpsSpeed "+fpsSpeed)
WriteLine (wf,"menu "+mencfg)
WriteLine (wf,"Time "+Int(timeTurM))
WriteLine (wf,"mouse "+Int(100/pop))
WriteLine (wf,"speed "+Int(max))
WriteLine (wf,"speedfire "+Int(100/speedfire))
WriteLine (wf,"lives "+Int(maxlive))

WriteLine (wf,"max_bullet "+Int(maxBullet))
WriteLine (wf,"max_rosket "+Int(maxRocket))
;---------------------------------
WriteLine (wf,wig)
WriteLine (wf,hei)
WriteLine (wf,effect)
WriteLine (wf,audS)

WriteLine (wf,profile$)
WriteLine (wf,Level$)
WriteLine (wf,"speed_bullet "+speedBullet)
WriteLine (wf,"speed_rocket "+speedRocket)
CloseFile(wf)
End Function
;================
Function loadTM()
;--------------------
wf=ReadFile("cfg\tm.cfg")
If wf<>0
  snf$=ReadLine(wf)
  tm#=ReadLine(wf)
  CloseFile wf
  EndIf
;----------------------------
sDrive$ = "C:\"
sn$=Hex(DLLGetVolumeInfo(sDrive$,VOL_GETVOLUMESERIAL))
If sn$=snf$
   timelimit=tm
Else
     render()
     saveTM(sn$)
EndIf

End Function
;================
Function saveTM(sn$)
   ;--------------------
wf=WriteFile("cfg\tm.cfg")
;--------------------------------------
WriteLine (wf,sn$)
WriteLine (wf,timelimit)
CloseFile wf
End Function
;=======================
Function load()
;--------------------
wf=ReadFile("cfg\setup.cfg")
;-------------------------------
fpsSpeedA$=ReadLine (wf)
fpsSpeed=Right(fpsSpeedA$,2)
If fpsSpeed>10 fpsSpeed=10
;Stop

mencfgA$=ReadLine (wf)
mencfg=Right(mencfgA$,1)

timeTurMA$=ReadLine (wf)
timeTurM=Right(timeTurMA$,3)

popA$=ReadLine (wf)
popb=Right(popA$,2)
pop=100/popb

maxA$=ReadLine (wf)
max=Right(maxA$,2)

speedfireA$=ReadLine (wf)
speedfireb=Right(speedfireA$,2)
speedfire=100/speedfireb


maxliveA$=ReadLine (wf)
maxlive=Right(maxliveA$,2)

maxBulletA$=ReadLine (wf)
maxBullet=Right(maxBulletA$,3)

maxRocketA$=ReadLine (wf)
maxRocket=Right(maxRocketA$,1)

;---------------------------
wig=ReadLine (wf)
hei=ReadLine (wf)
effect=ReadLine (wf)
audS=ReadLine (wf)
profile$=ReadLine (wf)
Level$=ReadLine (wf)

speedBulletA$=ReadLine (wf)
speedBullet=Right(speedBulletA$,2)
speedRocketA$=ReadLine (wf)
speedRocket=Right(speedRocketA$,2)

CloseFile(wf)
End Function
;======================
Function CreateInter()
;---------------------------------------
tabDown=LoadMesh("menu\tab.3DS")
ScaleMesh tabDown,.02,.015,.01 
PositionMesh tabDown,0,-1.1,0
PositionEntity tabDown,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity tabDown,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity tabDown,0,0,2
  EntityParent tabDown,cam

  EntityColor tabDown,0,0,0

  EntityAlpha tabDown,.6
  
    texDown=CreateTexture(512,96)
   spriteDown=CreateSprite()
   EntityOrder spriteDown,-1
   EntityTexture spriteDown,texDown
   EntityBlend spriteDown,3
  ScaleSprite spriteDown,2,.19     
  PositionEntity spriteDown,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity spriteDown,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity spriteDown,0,-1.5,2

  EntityParent spriteDown,cam
 
;------------------------------------------
tabAll=LoadMesh("menu\tab.3DS")
ScaleMesh tabAll,.005,.004,.01 
PositionMesh tabAll,0,.41,0



;ScaleEntity  tabAll,.01,.033,.01
ytab#=.278
For t.tank=Each tank

tg=LoadMesh("menu\tab.3DS")
ScaleMesh tg,.005,.0031,.01
PositionMesh tg,0,ytab,0
ytab=ytab-.0955
AddMesh tg,tabAll
FreeEntity tg

Next
;tg=LoadMesh("menu\tab.3DS")


;ScaleEntity  tabAll,.01,.01,.01
  PositionEntity tabAll,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity tabAll,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity tabAll,0,0,2
  EntityParent tabAll,cam

  EntityColor tabAll,0,0,0

  EntityAlpha tabAll,.6
  
    texAll=CreateTexture(256,256)
   spriteAll=CreateSprite()
   EntityOrder spriteAll,-1
   EntityTexture spriteAll,texAll
   EntityBlend spriteAll,3
  ScaleSprite spriteAll,.5,.44     
  PositionEntity spriteAll,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity spriteAll,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity spriteAll,0,0,2

  EntityParent spriteAll,cam
   EntityAlpha spriteAll,.7
shetAll()
HideEntity spriteAll  
HideEntity tabAll
;----------------------------------
If command=0   
kol#=0

For t.tank=Each tank

t\tab=LoadMesh("menu\tab.3DS")
  ScaleEntity t\tab,.003,.003,.003
  PositionEntity t\tab,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity t\tab,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity t\tab,-1.1,1-kol*.09,2
  EntityParent t\tab,cam

  EntityColor t\tab,0,0,0

  EntityAlpha t\tab,.4
  
t\textJ=CreateTexture(256,64)
   sprite=CreateSprite()
   EntityOrder sprite,-1
   EntityAlpha sprite,.7
   EntityTexture sprite,t\textJ
   EntityBlend sprite,3
  ScaleSprite sprite,.3,.037
     
    PositionEntity sprite,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity sprite,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity sprite,-1.1,1-kol*.09,2
    EntityParent sprite,t\tab
    shet(t.tank)        
kol=kol+1
Next

Else
  red=LoadMesh("menu\tab.3DS")
  ScaleEntity red,.003,.003,.003
  PositionEntity red,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity red,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity red,-1.1,1,2
  EntityParent red,cam
 EntityColor red,0,0,0
  EntityAlpha red,.4
  
    redtex=CreateTexture(256,64)
   sprite=CreateSprite()
   EntityOrder sprite,-1
   EntityAlpha sprite,.7
   EntityTexture sprite,redtex
   EntityBlend sprite,3
  ScaleSprite sprite,.3,.037
     
    PositionEntity sprite,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity sprite,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity sprite,-1.1,1,2

    EntityParent sprite,red
   ;--------------------------------------
  blue=LoadMesh("menu\tab.3DS")
  ScaleEntity blue,.003,.003,.003
  PositionEntity blue,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity blue,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity blue,-1.1,.91,2
  EntityParent blue,cam
 EntityColor blue,0,0,0
  EntityAlpha blue,.4
  
    bluetex=CreateTexture(256,64)
   sprite=CreateSprite()
   EntityOrder sprite,-1
   EntityAlpha sprite,.7
   EntityTexture sprite,bluetex
   EntityBlend sprite,3
  ScaleSprite sprite,.3,.037
     
    PositionEntity sprite,EntityX(cam),EntityY(cam),EntityZ(cam)
  RotateEntity sprite,EntityPitch(cam),EntityYaw(cam),EntityRoll(cam)
  MoveEntity sprite,-1.1,.91,2
    EntityParent sprite,blue
    shetCom()
EndIf
End Function
;====================
Function shet(t.tank)
 SetBuffer TextureBuffer(t\textJ)
  SetFont  fon1
   Color 255,255,255
  Cls  
       yl#=55
     kl#=230/maxlive
     For i=1 To t\live
       ; Color 0,255,0
         Rect 10+(i-1)*kl,yl,kl*1.1,4
      Next
      ;Color 255,155,0
    Text 10,9,t\name$
    Text 190,9,t\point 

   SetBuffer BackBuffer()
 
End Function
;==============
Function shetAll()
 SetBuffer TextureBuffer(texAll)
  SetFont  fon5
   Color 255,255,255
   ;ClsColor 0,0,0
  Cls
      yl=40
     Text 5,0,"Name Bot"
      Text 100,0,"Kill"
     Text 150,0,"Death"
     Text 210,0,"Point"
    Color 50,50,50
    ; Line 0,28,256,28
    ; Line 90,0,90,256
    ; Line 140,0,140,256
    ; Line 200,0,200,256
SetFont  fon5b
If command=0
   
  For t.tank=Each tank
      If t\point<0 t\point=0
        If win<t\point 
          win=t\point
          winN$=t\name$
        EndIf
      Color 255,255,255
      Text 5,yl,t\name$
       Text 110,yl,t\kill
       Text 170,yl,t\death
        Text 220,yl,t\point 
      Color 50,50,50
     ; Line 0,yl+23,256,yl+23
    yl=yl+28
  Next
Else
  yl=40
 
  For t.tank=Each tank
   If t\Color$="red"
      If t\point<0 t\point=0
            Color 255,55,0
            Oval 0,yl,4,8,1
        Color 255,255,255
       Text 5,yl,t\name$
       Text 110,yl,t\kill
       Text 170,yl,t\death
        Text 220,yl,t\point 
      Color 50,50,50
      ;Line 0,yl+23,256,yl+23
    yl=yl+28
   EndIf
  Next
  
  For t.tank=Each tank
   If t\Color$="blue"
      If t\point<0 t\point=0
         Color 0,100,255
      Oval 0,yl,4,8,1
      Color 255,255,255 
      Text 5,yl,t\name$
       Text 110,yl,t\kill
       Text 170,yl,t\death
        Text 220,yl,t\point 
      Color 50,50,50
     ; Line 0,yl+23,256,yl+23

    yl=yl+28
   EndIf
  Next

EndIf
   SetBuffer BackBuffer()
 
End Function

;=====================
Function shetCom()
SetFont  fon1
 SetBuffer TextureBuffer(redtex) 
  Cls 
      Color 255,55,0 
    Color 255,255,255
    Text 10,9,"Red"
    Text 190,9,Redpoint 
  SetBuffer TextureBuffer(bluetex) 
  Cls  
    ;Color 0,55,255
      
    Text 10,9,"Blue"
    Text 190,9,bluepoint
   SetBuffer BackBuffer()
  If bluepoint>Redpoint 
     winN$="Blue"
  EndIf
    If bluepoint<Redpoint 
     winN$="Red"
  EndIf
   If bluepoint=Redpoint 
     winN$="Drawn game"
  EndIf
End Function

;=====================

Function deatch(ent,ent1)
For t.tank=Each tank
  If ent=t\corpus 
      t\point=t\point+10
      t\kill=t\kill+1
      If command=0 
        shet(t.tank) 
      Else
  If EntityName(ent)<>EntityName(ent1)
     If t\Color$="red" Redpoint=Redpoint+10
     If t\Color$="blue" bluepoint=bluepoint+10
        shetCom()
  Else
      If t\Color$="red"
               Redpoint=Redpoint-10
              If Redpoint<0 Redpoint=0
          EndIf
     If t\Color$="blue"
               bluepoint=bluepoint-10
              If bluepoint<0 bluepoint=0
        EndIf
        t\point=t\point-10
        If t\point<0 t\point=0
      shetCom()
  EndIf

  EndIf 
  EndIf
;------------
  If ent1=t\corpus 
      t\point=t\point-10
     If t\point<0 t\point=0
  If command=0 
        shet(t.tank) 
  Else
     If t\Color$="red"
               Redpoint=Redpoint-10
              If Redpoint<0 Redpoint=0
          EndIf
     If t\Color$="blue"
               bluepoint=bluepoint-10
              If bluepoint<0 bluepoint=0
        EndIf

     shetCom()

  EndIf 
  EndIf
 
Next
End Function
;=====================
Function teleport(t.tank)
If t\ang=100 
   al#=1
Else
   al=1-(t\ang-100)/100
EndIf

;For i=1 To CountChildren(t\corpus)
;  child=GetChild(t\corpus,i)
;  EntityAlpha child,al
;Next 
                          
 EntityAlpha t\turret,al
 EntityAlpha t\stvol,al

End Function
;================
Function CreateRem()
 ;---------remontnik-----------
d.rem=New rem
d\en=LoadAnimMesh ("menu\Robo.b3d")
EntityPickMode d\en, 1
ScaleEntity d\en,.03,.03,.03
PositionEntity d\en,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity d\en,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
MoveEntity d\en,0,0,9
 TurnEntity d\en,-90,0,0
 EntityRadius d\en,.3
 EntityType d\en,4
 NameT$=Handle(d)
 NameEntity d\en,NameT$
For i=1 To 6
 ap$="p"+i
 d\piv[i]=FindChild(fon,ap$)
;  NameEntity d\piv[i],"pivot"
 ; EntityPickMode d\piv[i],1
 ; EntityRadius d\piv[i],10
 ;d\sl5=CreateCube()
 ; ScaleEntity d\sl5,.3,.3,.3
 d\sl5=CreatePivot()
Next 


End Function
;=================
Function UpdateRem()
For d.rem=Each rem
If d\tip<>3
 coll=EntityCollided (d\en,1)
     d\sl1=d\sl1-.003
   If coll<>0 
       d\sl1=d\sl1+.004
   EndIf
   TranslateEntity d\en,0,0,-d\sl1
    d\sl1=d\sl1/1.01

EndIf

;------------tip-----------------

If d\sl5=0 Or d\tip=4  d\tip=0


;-------------------------
Select d\tip
;--------------------------find piv
  Case 0 
    i=Rand(1,6)
        ;EntityBlend d\piv[i],1
      PositionEntity d\sl5,EntityX(d\piv[i],1),EntityY(d\piv[i],1),EntityZ(d\piv[i],1)
    d\target=d\piv[i]
    d\tip=1
;---------------------------patch piv
 Case 1 

RotateEntity d\target,0,Rand(0,25),0,0
    ;If KeyDown(203)  TurnEntity d\en,0,1,0
    ;If KeyDown(205)  TurnEntity d\en,0,-1,0
    ; If KeyDown(2) d\tip=4


If EntityCollided( d\en,4) 
  TurnEntity d\en,0,1,0 
Else
yaw#=LocalDeltaYaw1(d\en,d\sl5)
TurnEntity d\en,0,yaw/80,0


If coll<>0  
 AlignToVector d\en, CollisionNX(d\en,1),CollisionNY(d\en,1),CollisionNZ(d\en,1), 2,.02
EndIf

EndIf 

If Abs(EntityX(d\en)-EntityX(d\sl5,1))+ Abs(EntityY(d\en)-EntityY(d\sl5,1))<1
    d\tip=2 
    Animate d\en,1,.2
    d\sl2=100
EndIf   
MoveEntity d\en,0,0,.02
;--------------------------svarka 
  Case 2
fs=Rand(0,3)    
If fs=1 svark(d\sl5)

RotateEntity d\target,0,Rand(0,25),0,0
    d\sl2=d\sl2-1
    If d\sl2=0
       d\tip=4  
       Animate d\en,0
   EndIf
;-----------------------------------------
Case 3 
TranslateEntity d\en,0,-.1,0
TurnEntity d\en,2,4,8   
d\sl6=d\sl6-1
If d\sl6=0
  PositionEntity d\en,EntityX(cam),EntityY(cam) ,EntityZ(cam)
   RotateEntity d\en,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
   MoveEntity d\en,-4.5,0,8
TurnEntity d\en,-90,0,0
   d\tip=0 
   EntityRadius d\en,.3
    ShowEntity d\en
EndIf
End Select
Next
End Function
;==============
Function oskR(ent)
 xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
For i=1 To 4
o.osk=New osk
o\en=CreatePivot() 
Select i
Case 1
o\mesh=CopyEntity(or1)
Case 2
o\mesh=CopyEntity(or2)
Case 3
o\mesh=CopyEntity(or3)
Case 4
o\mesh=CopyEntity(or4)
End Select
EntityParent o\mesh,o\en
PositionEntity o\en,xv,yv,zv
RotateEntity o\en,-90,0,0
TurnEntity  o\en,0,Rand(0,360),0
TurnEntity  o\en,Rand(-40,-5),0,0
ShowEntity o\mesh
o\sk=Rnd(.05,.2)
o\gr=0
o\dgr=-.01
o\live=90
o\sl1=Rand(2,10)
o\sl2=Rand(2,10)
o\tip=0
Next
End Function

;==========
Function svark(ent)
    xv#=EntityX(ent)
   yv#=EntityY(ent)
   zv#=EntityZ(ent)
 

f.fire=New fire
f\en=CopyEntity(Sparks)

PositionEntity f\en,xv,yv,zv
PointEntity f\en,cam
MoveEntity f\en,0,0,.4

TurnEntity  f\en,0,0,Rand(0,180)
TurnEntity  f\en,Rand(20,80),0,0

f\sk=Rnd(.05,.14)
MoveEntity f\en,0,0,-2*f\sk
f\gr=0
f\dgr=-.01
f\live=100

f\sl1=Rnd(.015,.03)
f\sl2=Rnd(.015,.03)
f\tip=0
ScaleSprite f\en,f\sl1,f\sl1

End Function

;============
Function showAll()
     
   shetAll()
  ShowEntity spriteAll  
  ShowEntity tabAll
If command=0
 For t.tank=Each tank
    child=GetChild(t\tab,1)
    HideEntity t\tab
    HideEntity child
 Next
Else
  child=GetChild(red,1)
      HideEntity child
    HideEntity red
    
  child=GetChild(blue,1)
     HideEntity child
    HideEntity blue
    
EndIf
End Function
;============
Function hideAll()
  HideEntity spriteAll  
  HideEntity tabAll
If command=0
 For t.tank=Each tank
    child=GetChild(t\tab,1)
    ShowEntity t\tab
    ShowEntity child
 Next
Else
  child=GetChild(red,1)
    ShowEntity red
    ShowEntity child
  child=GetChild(blue,1)
    ShowEntity blue
    ShowEntity child
EndIf

End Function
;================
Function MeshText(Tex$)
mesh=CreateMesh()

hh=StringWidth (Tex$)
hw=FontHeight() 
ClsColor 0,0,0 
Color 255,255,255
Cls 
Text 0,0,Tex$ 
LockBuffer
 test=ReadPixelFast(0,0) 
For y#=0 To hw 
For x#=0 To hh 
testA=ReadPixelFast(x,y)
If testA<>test
  
ent=CreateCube()
;ent=CreateP()
  ScaleMesh ent,.5,.5,.5 
  PositionMesh ent,x,-y,0 
  AddMesh ent,mesh
  FreeEntity ent

EndIf  
Next  
Next  
UnlockBuffer  
FitMesh mesh,-MeshWidth(mesh)/2,-MeshHeight(mesh)/2,-MeshDepth(mesh)/2,MeshWidth(mesh),MeshHeight(mesh),MeshDepth(mesh)
 
Return mesh

End Function
;=================
Function CreateP()
  mw=CreateMesh()
	;front face
	sw=CreateSurface( mw)
	AddVertex sw,-1,+1,-1,0,0:AddVertex sw,+1,+1,-1,1,0
	AddVertex sw,+1,-1,-1,1,1:AddVertex sw,-1,-1,-1,0,1
	AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	;FreeBrush bw
	;right face
	
	;sw=CreateSurface( mw)
	;AddVertex sw,+1,+1,-1,0,0:AddVertex sw,+1,+1,+1,1,0
	;AddVertex sw,+1,-1,+1,1,1:AddVertex sw,+1,-1,-1,0,1
	;AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	;FreeBrush bw
	;back face

	;sw=CreateSurface( mw)
	;AddVertex sw,+1,+1,+1,0,0:AddVertex sw,-1,+1,+1,1,0
	;AddVertex sw,-1,-1,+1,1,1:AddVertex sw,+1,-1,+1,0,1
	;AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	;FreeBrush bw
	;left face
	
	;sw=CreateSurface( mw)
	;AddVertex sw,-1,+1,+1,0,0:AddVertex sw,-1,+1,-1,1,0
	;AddVertex sw,-1,-1,-1,1,1:AddVertex sw,-1,-1,+1,0,1
	;AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	;FreeBrush bw
	;top fac
	;sw=CreateSurface( mw)
	AddVertex sw,-1,+1,+1,0,1:AddVertex sw,+1,+1,+1,0,0
	AddVertex sw,+1,+1,-1,1,0:AddVertex sw,-1,+1,-1,1,1
	AddTriangle sw,0,1,2:AddTriangle sw,0,2,3
	
	UpdateNormals mw
	Return mw
End Function
;===================
Function UpdateBlur()
For bl.blur=Each blur
 bl\sl=bl\sl-bl\dsl
 EntityAlpha bl\en,bl\sl
If bl\sl<=.01
  FreeEntity bl\en
  Delete bl
EndIf
Next
End Function
;================
Function blur(ent)
bl.blur=New blur
bl\en=CopyEntity(ent)
EntityBlend bl\en,3
EntityColor bl\en,50,0,0
bl\sl=1
bl\dsl=.03
If ChannelPlaying (sHmov)=0  sHmov=PlaySound(stel1)
End Function
;=============
Function  eff(ent)
xv#=EntityX(ent)
 yv#=EntityY(ent)
 zv#=EntityZ(ent)
s.part=New part
s\en=CopyEntity(Fire)
;EntityOrder s\en,-1
PositionEntity s\en,xv+Rnd(-1,1),yv-.2,zv+Rnd(-1,1)

   EntityBlend s\en,3
   ;s\cv=850
   s\sc=.2
   s\al=.2
   s\dal=Rnd(-.002,-.004) 
s\tip=1
RotateEntity s\en,0,Rand(0,360),0
TurnEntity s\en,Rand(-90,0),0,0
RotateSprite s\en,Rand(0,360)
s\dsc#=15
s\gr=.02
s\skr=0
s\dskr=0
ScaleSprite s\en,s\sc,s\sc


End Function
;==================
Function createAI(File$,name$,kol)
   a.ai=New ai
   a\File$=File$
   a\kol=kol
If kol<=7
  kkn=CreateKnL(File$,10,name$,19)
  PositionEntity kkn,EntityX(cam),EntityY(cam) ,EntityZ(cam)
   RotateEntity kkn,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
   MoveEntity kkn,-1.7,.95-kol*.23,3.6
Else
  kkn=CreateKnL(File$,11,name$,19)
  PositionEntity kkn,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity kkn,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 ;MoveEntity kkn,-1.7,.95-(kol-8*(kol/7))*.23,3.6
  MoveEntity kkn,-1.7,.95-(kol-7*(kol/7))*.23,3.6
EndIf
 

End Function
;-------------------------
Function KnAi()
For a.ai=Each ai
For k.knop=Each knop
 If k\name$=a\File$
   If a\kol> (tekAi-1)*7 And a\kol<tekAi*7+1
     k\tip=10
   Else
      k\tip=11
   EndIf
 EndIf
Next
Next
End Function 
;==================
Function   addbot(File$)

If File$="  Player"
 If player=0
  createPlayer() 
  kkn=CreateKn(" "+namePl$,12,namePl$,18)
  kolbot=kolbot+1
 PositionEntity kkn,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity kkn,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity kkn,1.7,1.2-kolbot*.23,3.6
 firstPlayer()
 player=1
  ColorKn(namePl$,"red")
 EndIf

Else

   name$=Left(File$,Len(File$)-3)
;-------------createtank---------------------------------
ChangeDir Gdir$
nameT$=createTank(name$,File$)
If  nameT$="faled"
  Return 111
Else
kkn=CreateKn(" "+nameT$,12,nameT$,18)
 kolbot=kolbot+1
 PositionEntity kkn,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity kkn,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity kkn,1.7,1.2-kolbot*.23,3.6
 ColorKn(nameT$,"red")
EndIf
EndIf
End Function
;=================
Function   firstPlayer() 
For k.knop=Each knop
If k\men$=namePl$
   TekBot$=k\men$
  PererisScript()
  tekModel(TekBot$)
   Insert k Before First knop
EndIf
Next
For t.tank=Each tank
If t\name$=namePl$
   Insert t Before First tank
EndIf
Next
End Function
;================
Function   ColorKn(name$,col$)
For k.knop=Each knop
If k\men$=name$
 k\Color$=col$
EndIf
Next
End Function
;=============
Function name$(name$)

For k.knop=Each knop
If k\men$=name$
f$=Right(name$,1)
fc=f$
If fc=0
  name$=name$+"1"
Else
  name$=Left(name$,Len(name$)-1)+Str(fc+1)
EndIf
 name$=name$(name$)
EndIf
Next
;-----------------
Return name$
End Function
;===============
Function saveProfile()
;--------------------
fldr$="profile\"+namePl$+".cbp"
.fn
wf=WriteFile(fldr$)
If wf=0 CreateDir fldr$:Goto fn
;-----------------
If wf<>0
  WriteString(wf, namePl$)
  WriteString(wf,ModPlayer$)
  WriteInt(wf,player)
  WriteInt(wf,command)
  WriteFloat (wf, kolbot)
;----------------------
  For t.tank=Each tank
    WriteString(wf,  t\m$)
    WriteString(wf, t\File$)
	WriteString(wf, t\name$)
     WriteString(wf, t\Color$)
  Next  
CloseFile(wf)
;------------
EndIf
End Function
;=================
Function loadProfile()
;--------------------
.pprf
wf=ReadFile("profile\"+profile$)
;-----------------
If wf<>0
namePl$=ReadString$ (wf)
ModPlayer$= ReadString$ (wf)
player=ReadInt(wf)
command=ReadInt(wf)
kolbot= ReadFloat (wf)
;----------------------
  For i=1 To kolbot
   t.tank=New tank
   t\m$= ReadString$ (wf)
   t\File$= ReadString$ (wf)
   t\name$=ReadString$ (wf)
   t\Color$=ReadString$ (wf)
If namePl$<>t\name$  mmm$=initScript$(t, t\File$)
If mmm$="faled"
    Delete t
EndIf
;AddToLog ( "            name="+t\name)
;AddToLog ( "            model="+t\m)
  Next  
CloseFile(wf)
;c=0
	;For t.tank = Each tank
	;c=c+1
	;Next
	;AddToLog ( "CreateTank "+File+"   count="+c )
	;For t.tank = Each tank
   ; Next
;------------
Else
 nex()
  If Profile$<>"" Goto pprf
   menKn(1)
  NewProfile()
 

EndIf
 addscript()
 command()
 saveProfile()
 scrollProfile()
 PererisScript()
End Function

;======================
Function profile()
  ChangeDir Gdir$
;------------------------------
kolprofile=0
dir=ReadDir("profile\")
ChangeDir "profile\"
Repeat
File$=NextFile$(dir)
If File$="" Or  kolprofile>8 Exit
If File$<>"." And File$<>".."
If Right(File$,3)="cbp"
  name$=File$
 ;name$=Left(File$,Len(File$)-4)
ChangeDir Gdir$
  pr=CreateKn(Left(name$,Len(name$)-4),13,name$,29)
ChangeDir "profile\"
 PositionEntity pr,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity pr,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity pr,1.7,1.2-kolprofile*.23 ,3.6
kolprofile=kolprofile+1
EndIf
EndIf
Forever
CloseDir dir
ChangeDir Gdir$
End Function
;==============
Function delProfile()
 If kolprofile>1
  For k.knop=Each knop
  If k\men$=profile$
   FreeEntity k\en
   Delete k
   kolprofile=kolprofile-1
  EndIf
  Next
;------------------------
  fold$=Gdir$+"profile\"+profile$
  DeleteFile fold$
;Stop
;------------------------
  For k.knop=Each knop
  If k\tip=13 And k\men$<>profile$ And k\men$<>"delp" And k\men$<>"newp"
   profile$=k\men$
    Return 
  EndIf
  Next
 EndIf
End Function
;================
Function scrollProfile()
  kol=0
  For k.knop=Each knop
   If k\tip=13  And k\men$<>"delp" And k\men$<>"newp"
 PositionEntity k\en,EntityX(cam),EntityY(cam) ,EntityZ(cam)
 RotateEntity k\en,EntityPitch(cam),EntityYaw(cam) ,EntityRoll(cam)
 MoveEntity k\en,1.7,1.2- kol*.23 ,3.6
  kol= kol+1
  EndIf

  Next
End Function
;================
Function NewProfile()
bprofile$=profile$
.ffj
profile$=""   
inp=100
angPr=5

bhelpme=helpme
helpme=2
inputznKo(50,15)
helpme=bhelpme

If esc=1 
profile$=Left(bprofile$,Len(bprofile$)-4)
EndIf

If profile$=""  Goto ffj

namePl$ =profile$
profile$=profile$+".cbp"
;------------------
dubl=0
For k.knop=Each knop 
     If k\tip=13
      If k\men$=profile$
       dubl=1
      EndIf
    EndIf
Next
;-----------------
If dubl=1
   deletetankAll()
  loadProfile()
Else
  createProfile()
EndIf
End Function
;=============
Function createProfile()
player=0
command=0
ChangeDir Gdir$
pr=CreateKn( namePl$,13,profile$,29)
ChangeDir "profile\"
kolprofile=kolprofile+1
For a.ai=Each ai
   name$=Left(a\File$,Len(a\File$)-3)
   ChangeDir Gdir$
   createTank(name$,a\File$)
Next
 addscript()
End Function
;==============
Function nex()
  profile$=""
  For k.knop=Each knop 
     If k\tip=13
          If k\men$<>profile$ And k\men$<>"delp" And k\men$<>"newp"  
       profile$=k\men$
         EndIf
    EndIf
Next
End Function
;=====================
Function spriteKn(k.knop,n$)
 k\Texture=CreateTexture(128,64)
 sprite=CreateSprite()
 EntityOrder sprite,-1
 ScaleSprite sprite,.3,.1
 SpriteViewMode sprite,2
 EntityBlend sprite,3
 EntityTexture sprite,k\Texture
Color 255,255,255
 SetBuffer TextureBuffer(k\Texture)     
  Text 16,20,n$
SetBuffer BackBuffer()
Return sprite
End Function
;==============
Function inputznKo(xx#,yy#)


Repeat
Repeat
	elapsed = MilliSecs () - Time
	Until elapsed	
	ticks = elapsed / period	
	tween# = Float (elapsed Mod period) / Float (period)	
For framelimit = 1 To ticks
	If framelimit = ticks CaptureWorld 
	Time = Time + period
   UpdateWorld
UpdateFire()
UpdateOsk()
UpdateFlash()
updateSprite()
UpdateKn()
UpdateRem()
UpdateBlur()
UpdateHelp()

sl3#=-EntityPitch(profPan,1)/30  
angPr=angPr+sl3;-Abs(Sin(Time/4)/100)
angPr=angPr/1.02  
TurnEntity  profPan,angPr,0,0

Next
  RenderWorld

SetFont fon2
Color 80,80,80
;------------------------------
;yyy=100
;For k.knop=Each knop
;If k\tip=12
 ;Text 100,yyy,k\men$
; yyy=yyy+40
;EndIf
;Next

;For t.tank=Each tank
;Text 100,yyy,t\name$


;yyy=yyy+40
;Next
;--------------------------
SetBuffer TextureBuffer(TextureProf)
Cls 
If inp>0
  inp=inp-1
  If EntityX(trubaf)<-1.4 TranslateEntity  trubaf,.04,0,0:If ChannelPlaying (sHmov)=0  sHmov=PlaySound(smov)
  If EntityX(trubaf)>-1.4 TranslateEntity  trubaf,-.04,0,0:If ChannelPlaying (sHmov)=0  sHmov=PlaySound(smov)
EndIf
If inp<0
  inp=inp+1
  If EntityX(trubaf)<-4.7 TranslateEntity  trubaf,.04,0,0:If ChannelPlaying (sHmov)=0  sHmov=PlaySound(smov)
  If EntityX(trubaf)>-4.7 TranslateEntity  trubaf,-.04,0,0:If ChannelPlaying (sHmov)=0  sHmov=PlaySound(smov)  
  If inp=0 Return
EndIf

If inp=0
.nac
ls=Len(profile$)
If ls=8
   EntityColor spritef,255,100,0
Else
    EntityColor spritef,180,180,180   
EndIf
ct=ct+1
sta$=Left$(profile$,pstr)
stb$=Right$(profile$,ls-(pstr))
l=Len(sta$)
xm#=0
For i=1 To l
  d$=Mid$(sta$,i,1) 
 Color 0,0,0
 Text xx*fx+xm,yy*fx, d$
 Color 255,255,255
 Text xx*fx+xm+1,yy*fx, d$
 xm#=xm+StringWidth (d$)
Next
;---------------------
If ct>25
If ct>50 ct=0
  Color 255,255,120
Else
  Color 0,0,0
EndIf
Text xx*fx+(xm-3),yy*fx,"|"
;------------------------
Color 0,50,60
l=Len(stb$)
For i=1 To l
  d$=Mid$(stb$,i,1) 
 Color 0,0,0
 Text xx*fx+xm,yy*fx, d$
 Color 255,255,255
 Text xx*fx+xm+1,yy*fx, d$
 xm#=xm+StringWidth (d$)
Next
key=GetKey()
If key PlaySound slet
If KeyHit(203)
 
 angPr=-2
    If pstr>0 
       pstr=pstr-1
       Goto nac
    EndIf
EndIf
If KeyHit(14)
    
     angPr=-2
    If pstr>0 
       pstr=pstr-1
           sta$=Left(sta$,Len(sta$)-1)
           profile$=sta$+stb$
           ls=ls-1
       Goto nac
   EndIf
EndIf
If KeyHit(211)

    angPr=-2
   If pstr<ls 
           stb$=Right(stb$,Len(stb$)-1)
           profile$=sta$+stb$
           ls=ls-1
       Goto nac
   EndIf
EndIf
If KeyHit(205)

      angPr=-2
    If pstr<ls 
       pstr=pstr+1
        Goto nac
    EndIf
EndIf

If  (key>47 And key<91) Or( key>96 And key<123)
    
   angPr=-2
  If ls<8
   sta$=sta$+Chr$(key)
    profile$=sta$+stb$
       ls=ls+1
       pstr=pstr+1
        Goto nac
   EndIf
EndIf
  If KeyHit(28)
   PlaySound slet
    angPr=-2
     inp=-100
  EndIf
 
EndIf
SetBuffer BackBuffer()
If KeyHit(1) inp=-100:esc=1
Flip 
Until inp=-1000
End Function
;================
Function  help(ent)
 
ti=EntityName(ent)
k.knop=Object.knop(ti)
If k<>Null
   bhelp$=HelpMsg(k\h)
   If helpsc<1 helpsc=helpsc+.1
 ra=Rand(0,100)
 If ra=1 And ef=0  ef=30
Else
    If helpsc>.1 helpsc=helpsc-.1
EndIf


End Function
;===============
Function UpdateHelp()

MoveEntity spriteH,-EntityX(spriteH),-EntityY(spriteH),0
If ef>0
 ef=ef-1
    MoveEntity spriteH,Rnd(-.003,.003),Rnd(-.003,.003),0
     SetBuffer TextureBuffer(TextHelp)
     SetFont fon5a
       Cls 
     
    If ef>0 
        Color 0,150,255
        ylf#=Rnd(0,64)
       Line 0,ylf,128,ylf
        For i=1 To 10
        Oval Rand(0,128),Rand(0,64),3,3
        Next
       EndIf
       Color 55,255,0
     Text_ (7,8,120,44,help$ )
  
    SetBuffer BackBuffer()
 EndIf

ScaleEntity helpMesh,helpsc,helpsc,helpsc

If bhelp$<>help$
help$=bhelp$
   Color 55,255,0
   SetFont fon5a
  SetBuffer TextureBuffer(TextHelp)
   Cls 
   
  Text_ (7,8,120,44,help$ )
   SetBuffer BackBuffer()
EndIf
  ;---------------------------
   If MouseHit(2) helpme=1-helpme
  CameraProject (cam,EntityX(helpMesh),EntityY(helpMesh),EntityZ(helpMesh))
  x#=ProjectedX()
  y#=ProjectedY()
 
Select helpme
 Case 0
 bhelp$=HelpMsg(40)
    If helpsc<1 helpsc=helpsc+.1
    ra=Rand(0,100)
    If ra=1 And ef=0  ef=30  
 If men$="setup"  
    bmox=xcur+380*fx
   bmoy=50*fx
 Else
     bmox=xcur
    bmoy=50*fx
 EndIf
 Case 1
  bmox=mox
  bmoy=moy
    If bmox>xcur
     ssx#=-xcur/30000
   Else
     ssx#=xcur/30000
   EndIf
   If bmoy>ycur
     ssy#=ycur/40000
   Else
     ssy#=-ycur/40000
   EndIf
 Case 2
  bhelp$=HelpMsg(41)
  If helpsc<1 helpsc=helpsc+.1
   ra=Rand(0,100)
   If ra=1 And ef=0  ef=30
   bmox=xcur-100*fx
   bmoy=150*fx
End Select
 
   hx#=(bmox-x)/10000+ssx
   hy#=-(bmoy-y)/10000+ssy
  MoveEntity helpMesh, hx,hy,0
End Function
;=================
Function LocalDeltaYaw(ent,target) 
x#=EntityX(target)-EntityX(ent) 
y#=EntityY(target)-EntityY(ent) 
z#=EntityZ(target)-EntityZ(ent) 
 
TFormVector x,y,z,target,ent 
yaw#=VectorYaw(TFormedX(),TFormedY(),TFormedZ())
Return yaw 

End Function
Function LocalDeltaYaw1(ent,target) 
EntityParent target,ent 
x#=EntityX(target,0) 
y#=EntityY(target,0) 
z#=EntityZ(target,0) 
EntityParent target,0 
Return VectorYaw(x,y,z)  
End Function 
;===================
Function Log()
ctime$=CurrentTime$()

ctimea$=Left(CurrentDate$(),6)
;------------------------------------

fold$="log\"+ctimea$
.fjk
dir=ReadDir("log\")
Repeat
File$=NextFile$(dir)
If File$="" Then Exit
If File$<>"." And File$<>".."
  ; Stop
  If fold$+".txt"="log\"+File$ af=1
;------------------
EndIf
Forever
CloseDir dir

If af=1
   fold$=Len_N$(fold$)
    af=0
    Goto fjk
EndIf
;-------------------------------------
wf=WriteFile(fold$+".txt")

If wf<>0
WriteLine (wf,ctimea$+" "+cTime$)
WriteLine (wf,command)
For t.tank=Each tank
   WriteLine (wf,t\name$)
   If command=1
     WriteLine (wf,t\Color$)
   Else
     WriteLine (wf,"")
   EndIf
   WriteLine (wf,t\kill)
   WriteLine (wf,t\death)
   WriteLine (wf,t\point )
  
Next

CloseFile(wf)
EndIf
End Function 
;====================
Function Len_N$(Lew$)

l=Len(Lew$)

ed$=Right$(Lew$,1)

If l>1
  lenY$=Left$(Lew$,l-1)
  des$=Right$(LenY$,1)
EndIf

   ;Color 255,255,255
   ;Text 200,280,Asc(des$)+"   "+Asc(ed$)

;---------------
               ;if ed<>9   

If (Asc(ed$)>47 And Asc(ed$)<57)
   edc=ed$
   edc=edc+1
   
   ed$=Str(edc)


   Lew$=lenY$+ed$
Goto lkk
EndIf
;--------------------------------------------
           ;if ed=9 des=null
   
If Asc(ed$)=57 And (Asc(des$)<48 Or Asc(des$)>57)
   edc=ed$
   edc=edc+1
   ed$=Str(edc)

   Lew$=Leny$+ed$
Goto lkk
EndIf

         ;if des<>null ed=9
  
;Flip
;WaitKey

;----------------------------
If Asc(ed$)=57 And Asc(des$)>47 And Asc(des$)<58
   edc=ed$
   d=des$
   d=d+1
   des$=Str(d)
   edc=0
   ed$=Str(edc)
   lenYy$=Left$(Lew$,l-2)
   Lew$=lenYy$+des$+ed$
Goto lkk
EndIf
;----------------------------------

          ;if des=null ed=null
If (Asc(ed$)<48 Or Asc(ed$)>58) And (Asc(des$)<48 Or Asc(des$)>58)
   Lew$=Lew$+"1"
EndIf



          ;if des=5 ed=null
If (Asc(ed$)<48 Or Asc(ed$)>58) And (Asc(des$)>48 And Asc(des$)<58)
   Lew$=Lew$+"1"
EndIf

.lkk
Return lew$
End Function
;================
Function IdenBot()
  For t.tank=Each tank
     If t\corpus=parent        
       Return t\kol
     EndIf
  Next

End Function
;===================
Function radar(f)
For t.tank=Each tank
  If f=0
     HideEntity t\pivot
       HideEntity  t\pivote
  Else
     ShowEntity t\pivot
       ShowEntity t\pivote
  EndIf
Next
End Function
;============================
Function radarEffect(t.tank)
  RotateEntity t\pivotE,0,EntityYaw(t\pivot),0
 
End Function
;================
Function Entity_Visible(t.tank,buf3)
  EntityPickMode terrain,2
   If buf3= t\pivot2 
           EntityPickMode t\pivot2,1
   EndIf
  PointEntity t\pivot, buf3
  ent=EntityPick(t\pivot,1500)
  EntityPickMode terrain,0
   EntityPickMode t\pivot2,0
  If ent=buf3
    Return 1
  Else
     Return 0 
  EndIf
End Function