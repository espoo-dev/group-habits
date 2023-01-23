<script lang="ts">
  import AxiosAdapter from './infra/http/AxiosAdapter';

  const api = new AxiosAdapter();
  let auth = '';

  const menuOptions = [
    {
      name: 'Início',
    },
    {
      name: 'Categorias',
    },
  ];

  const login = async () => {
    api
      .post('https://group-habits.herokuapp.com/api/v1/users/sign_in', {
        user: {
          email: 'user@email.com',
          password: '123456789',
        },
      })
      .then((resp) => {
        auth = resp.user.authorization;
      });
  };

  login();

  const habits = [
    {
      name: 'Drink water',
      icon: 'heart',
      selected: false,
    },
    {
      name: 'Go to the gym',
      icon: 'heart',
      selected: true,
    },
  ];
</script>

<main>
  <!-- <Menu options={menuOptions} /> -->
  <h1>login</h1>
</main>

<style>
  .list-container {
    display: flex;
    gap: 8px;
  }

  /*  */
  :global(body) {
    background-color: #f9f9f9;
    color: #000;
  }
  .page-container {
    padding: 8px;
  }
  nav {
    padding: 8px;
    color: #eaeaea;
    text-align: left;

    font-size: 22px;
    font-weight: bold;
  }
</style>
