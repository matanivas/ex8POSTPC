A lightweight application that communicate with remote server.

sends users data to be stored by the server and also poll users data from the server to show on the UI.


three main layers:
1. server layer, that has implementations for functions that call the server and return results

2. flow layer - some pre-defined flows that call the methods in the "server" layer and handle the results and\or problems (flight mode, server not responding, ...)

3. UI layer - that calls objects from the flow layer

features:
1. connect the user
On the first time the user is using your app, ask him\her to give you a username (should be a string, only letters and digits allowed, no whitespaces or special characters)

2. get the most up-to-date user info.
each time the user launches the app, query the server at the GET endpoint at -

http://hujipostpc2019.pythonanywhere.com/user/
to get the user's info.

The server's response should be a simple json of the type:
{ "data": { "username": <String or null>,
            "pretty_name": <String or null>,
            "image_url": <String or null> 
           }
 }
Until response, shows "loading..." progress on the UI.
Once got the info, shows the user's name on the ui.

3. allows user to change their names

4. managing the user's profile image.


