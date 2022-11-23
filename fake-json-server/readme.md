# Mock backend api

1. `cd fake-json-server`
1. `npm install -g json-server`
1. `json-server --watch db.json --port 3004 --middlewares ./middleware.js`
1. do a post request like

   1. ```
      POST http://localhost:3004/sign_in
       Host: localhost:3000
       Content-Type: application/json
       {
         "user":	{
           "email": "user@example.com",
           "password": "password"
         }
       }
      ```

1. store the value `authorization` of the response header
