Type tank
Field name$  ;��� ������
Field corpus  ;������ �������
Field turret    ;������ �����
Field stvol       ;������ ������
Field sh               ;����
;-------------������-------------
;�������
Field xc#
Field yc#
Field zc#
;����
Field rxc#
Field ryc#
Field rzc#
;-------------�����-----------
;�������
Field xt#
Field yt#
Field zt#
;����
Field rxt#
Field ryt#
Field rzt#
;--------------�����----------
;�������
Field xs#
Field ys#
Field zs#
;����
Field rxs#
Field rys#
Field rzs#
;-------------------bonus--------------------------
Field bullet
Field rocket
Field DamageYaw#
;----------------------radar----------------------
;Field frend
;Field enemy
;Field Rbullet
;Field Rrocket
;Field Repair

;Field strukt#
;Field wall#
;-----------��������� ����������-------------
;������� resp
Field xres#
Field yres#
Field zres#

Field height#
Field v#
Field og
Field inf
Field tt
Field texa
Field vihlop
Field al#
Field speed 
Field speed2      
Field xrrd#            
Field zrrd#       
Field gravity#    
Field live
Field m$    ;tip model
Field sc.script
Field ang
Field pivot
Field pivot2
Field pivotE
Field pivot1
Field kol
Field point
Field death
Field kill
Field textJ
Field Color$
Field sound
Field soundP#
Field guns
Field tab
Field File$
Field dify#
Field wh1
Field wh2
Field wh3
Field wh4
Field pivWh1
Field pivWh2
Field trx#
Field trz#
Field anglD#
Field anglc#
;------------�� ����������------------
Field collignt  ;������� � ������ ������
Field collignP  ;������� � ������������
Field collignW   ;������� �� ������

Field angl#  ;������� �� ������� �����
Field move#  ;������� �� ��������
Field z#     ;�������� ��������
Field angly# ;������� �� ������� �����
Field anglx# ;������� �� ������� ������ 

Field target ;���� ����� (����� ����������)
Field fire ;������� �� ������� �����
Field fire2 ;������� �� ������� ������
End Type
;=========================
Type objekt
	Field en             ;model
	Field enK
	Field sc#            ; scale
	Field x#             ;posicia
	Field y#
	Field z#
	
	Field xr#           ;angle
	Field yr#
	Field zr#
	Field xtr#
	Field ztr#
	
	Field name$    ;ima (trigger)
	
	Field kind$
	Field types$
	Field destroy$="NO"
	Field trigger$="NO"
	Field trigger1$="NO"
	Field trigger2$="NO"
	Field trigger3$="NO"
	Field action1$="NO"
	Field action2$="NO"
	Field action3$="NO"
	Field atrigger$="NO"
	Field ttime$="000"
	Field r#
	Field g#
	Field b#
	Field parent$
	Field patch$
	Field skor$
	Field ran#
	Field child$
End Type
;==================
Type fire
  Field en
  Field live#
  Field sk#
  Field gr#
  Field dgr#
  Field sl1#
  Field sl2#
  Field tip
  Field ent
   Field target
End Type
;----------------------
Type osk
  Field en
   Field mesh
  Field live#
  Field sk#
  Field gr#
  Field dgr#
  Field sl1#
  Field sl2#
  Field tip
End Type
;----------------------
Type fisik
  Field en
  Field mesh
  Field live#
  Field sk#
  Field gr#
  Field dgr#
  Field sl1#
  Field sl2#
  Field tip
End Type
;-------------------------
Type Flash
  Field en
  Field sc#
  Field dsc#
  Field psc#
  Field ssc#
   Field liv
   Field gr#
   Field dgr#
  Field tip
End Type
;============
Type Anim
  Field en
   Field fr#
  Field sc#
  Field dsc#
  Field al#
  Field dal#
   Field liv
   Field gr#
   Field Tex
   Field frame#
    Field dfr#
  Field tip
End Type
;=============
Type part
  Field en
  Field sc#
  Field dsc#
  Field skr#
  Field dskr#
  Field al#
  Field dal#
  Field rot#
  Field drot#
  Field gr#
  Field dgr#
   Field live#
   Field dlive#
    Field cv#
    Field tip
End Type

Type ter
	Field en
	Field Tex
	Field sizew#
	Field sizeh#
End Type
;-----------------
Type knop
Field spt#
Field pivot
Field en
Field enP
Field Text
Field name$
Field tip
Field men$
Field sl1#
Field sl2#
Field sl3#
Field Color$
Field h
Field texture
End Type
;--------------------
Type model
Field en
Field Model
End Type
;-------------------
Type lev
Field level$
Field img
End Type
;----------------------
Type angar
 Field kol
 Field x#
 Field y#
 Field z# 
End Type
;-------------------------
Type robot
Field en
Field n
Field name$
End Type
;-------------------------------
Type rem
Field en
Field tip
Field sl1#
Field sl2#
Field sl3#
Field sl4#
Field sl5
Field piv[6]

Field sl6
Field target
End Type
;------------------
Type blur
Field en
Field sl#
Field dsl#
End Type
;-----------------------
Type ai
Field File$
Field kol
End Type
;-----------------
Type bonus
Field en
Field tip
Field Time#
End Type








;-----------------------------------------------------------------------------------------