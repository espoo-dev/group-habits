export type AccountModel = {
  user: {
    email: string;
    provider: string;
    uid: string;
    id: number;
    allow_password_change: false;
    first_name: string;
    last_name: string;
    username: string;
    group_id: number;
    authorization: string;
  };
};
