Global DebFont

Function InitDebugger()
	DebFont=LoadFont("arial", 14)
End Function

Function DrawDebugger(t.tank, x=10, y=50)
	SetFont DebFont
	For k = 1 To t\sc\intVarC
		Color 0, 0, 0
		s$ = t\sc\intVarN [ k ]+" = "+PeekFloat( t\sc\var, (k-1)*4 )
		Text x-1, y-1, s
		Text x-1, y+1, s
		Text x+1, y-1, s
		Text x+1, y+1, s
		Color 255, 255, 0
		Text x, y, s
		y=y+FontHeight()
	Next
End Function
;=======================
Function DrawDebuggerA(x,y)
	SetFont DebFont
For t.tank=Each tank
   If t\corpus=parent
   Text x, y, t\name$

	For k=1 To 29
	
		s$ = t\sc\intVarN [ k ]+" = "+PeekFloat( t\sc\var, (k-1)*4 )
		
		;Color 255,255,255
		Text x, y+20, s
		y=y+FontHeight()
	Next
EndIf
Next
End Function
;========================
Function DrawDebuggerB(x,y)
	SetFont DebFont
For t.tank=Each tank
  If t\corpus=parent
   Text x, y, t\name$

	For k=36 To t\sc\intVarC
	
		s$ = t\sc\intVarN [ k ]+" = "+PeekFloat( t\sc\var, (k-1)*4 )
		
		;Color 255,255,255
		Text x, y+20, s
		y=y+FontHeight()
	Next
EndIf
Next
End Function

Function AddToLog ( s$ )
	file$ = gDir + "log.txt"
	f = OpenFile ( file )
	SeekFile ( f, FileSize ( file ) )
	WriteLine f, s
	CloseFile f
End Function

Function ClearLog ( )
	file$ = gDir + "log.txt"
	f = WriteFile ( file )
	WriteLine f, "Game started "+CurrentTime ( )
	CloseFile f
End Function