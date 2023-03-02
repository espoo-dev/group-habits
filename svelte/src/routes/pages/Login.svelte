<script lang="ts">
  import { makeRemoteAuthentication } from '../../main/factories/usecases/remote-authentication-factory';
  import { navigate } from 'svelte-routing';
  import { setCurrentAccountAdapter } from '../../main/adapters';
  import { notifications } from '../../../src/infra/notification/notification';
  import Select from '../../../src/lib/components/Select/Select.svelte';
  import { makeRemoteSalesUnits } from '../../../src/main/factories/usecases/remote-sales-unit-factory';

  const user = {
    email: 'user@email.com',
    password: '123456789',
  };

  let selected: string | number;

  const http = makeRemoteAuthentication();

  const login = async () => {
    const response = await http.auth({ user });
    if (response.user) {
      navigate('/categories', { replace: true });
      setCurrentAccountAdapter(response);
      notifications.success('Bem vindo!', 3000);
    }
  };
</script>

<main class="page-container">
  <div class="menu-container">
    <div class="inner-menu">
      <h1 style="margin-bottom: 20px;">Login</h1>

      <div class="form-container">
        <input
          type="email"
          bind:value={user.email}
          name="username"
          id="username"
          placeholder="Email"
        />

        <input
          type="password"
          name="password"
          id="password"
          placeholder="Senha"
          bind:value={user.password}
        />

        <button on:click={login}>Entrar</button>

        {selected}
      </div>
    </div>
  </div>
</main>

<style>
  :global(body) {
    background-color: #f9f9f9;
    color: #000;
    margin: 0;
  }

  .page-container {
    padding: 8px;
    height: 100vh;
  }

  .menu-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;
  }

  .form-container {
    display: flex;
    flex-direction: column;
    gap: 16px;
  }

  .inner-menu {
    border: 1px solid #ececec;
    border-radius: 8px;
    padding: 30px;
    background-color: #fff;
    min-width: 30%;
  }

  input {
    border-bottom: 1px solid #ececec;
  }

  input:focus-visible {
    border-bottom: 1px solid #fe724c;
    outline: none;
  }

  button {
    background-color: #fe724c;
    color: #ffffff;
    border-radius: 8px;
    padding: 8px;
  }

  button:hover {
    background-color: #e16341;
  }
</style>
