import { REACT_APP_API_URL } from "@env";
import { rest } from 'msw';

export const handlers = [
  rest.post(`${REACT_APP_API_URL}/v1/users/sign_in`, (_req, res, ctx) => {
    return res(
      ctx.status(200),
      ctx.json({

        user: {
          allow_password_change: false,
          authorization: "Bearer eyJhY2Nlc3MtdG9rZW4iOiJ6RjFuaDk0OHMxZ2VYTktBWl9NYzh3IiwidG9rZW4tdHlwZSI6IkJlYXJlciIsImNsaWVudCI6IkViWWxOTzN5ZHdEZE1DV3Z1QU9KSWciLCJleHBpcnkiOiIxNzQwMDAzNjA4IiwidWlkIjoidXNlckBlbWFpbC5jb20ifQ==",
          email: "email@email.com",
          first_name: "user",
          group_id: 1,
          id: 2,
          last_name: "email",
          provider: "email",
          uid: "user@email.com",
          username: "useremail"
        }
      }),
    )
  }),
]
