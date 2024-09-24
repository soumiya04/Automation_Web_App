#include <MsgBoxConstants.au3>
ControlFocus ( "[CLASS:ToolbarWindow32]", "", "1001" )

ControlClick ( "[CLASS:ToolbarWindow32]", "", "1001" )

Send("C:\Users\Public\Pictures{Enter}")
Sleep(1000)
ControlFocus ( "Open", "", "Edit1" )

ControlSetText ( "Open", "", "Edit1", "Testing.jpg" )

ControlClick ( "Open", "", "Button1")
Sleep(500)