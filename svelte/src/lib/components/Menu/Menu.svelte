<script lang="ts">
  import {
    getCurrentAccountAdapter,
    setCurrentAccountAdapter,
  } from '../../../main/adapters';

  interface Option {
    name: string;
    link: string;
  }

  let options: Option[] = [
    { name: 'Categorias', link: '/categories' },
    { name: 'Clientes', link: '/customers' },
    { name: 'Items', link: '/items' },
  ];

  const account = getCurrentAccountAdapter();
  let showMinimenu = false;

  const toggleMenu = () => {
    showMinimenu = !showMinimenu;
  };

  const logout = () => {
    setCurrentAccountAdapter(undefined);
    window.location.href = '/';
  };
</script>

<nav
  class="bg-white border-gray-200 px-2 sm:px-4 py-2.5 rounded dark:bg-gray-900"
>
  <div class="container flex flex-wrap items-center justify-between mx-auto">
    <div class="flex" style="gap: 28px">
      <a
        href="https://github.com/espoo-dev/group-habits"
        class="flex items-center"
      >
        <img
          style="border-radius: 50%;"
          src="https://cdn.dribbble.com/userupload/4468594/file/original-8a4c22204c50dbaceb54e54a81263c6a.jpg?compress=1&resize=1024x768"
          class="h-6 mr-3 sm:h-9"
          alt="Flowbite Logo"
        />
        <span
          class="self-center text-xl font-semibold whitespace-nowrap dark:text-white"
          >Sistema O.S.</span
        >
      </a>

      <div
        class="items-center justify-between hidden w-full md:flex md:w-auto md:order-1"
        id="mobile-menu-2"
      >
        <ul
          class="flex flex-col p-4 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700"
        >
          {#each options as option}
            <li>
              <a
                href={option.link}
                class="block py-2 pl-3 pr-4 rounded md:bg-transparent md:p-0 dark:text-white hover:text-blue-600"
              >
                {option.name}
              </a>
            </li>
          {/each}
        </ul>
      </div>
    </div>

    <div class="flex items-center md:order-2">
      <button
        on:click={toggleMenu}
        data-testid="menu-user-profile"
        type="button"
        class="flex mr-3 text-sm bg-gray-800 rounded-full md:mr-0 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600"
      >
        <span class="sr-only">Open user menu</span>
        <img
          class="w-8 h-8 rounded-full"
          src="https://s.aficionados.com.br/imagens/5-fatos-insanos-sobre-rocket-raccoon-dos-guardioes-da-galaxia_f.jpg"
          alt="user photo"
        />
      </button>

      {#if showMinimenu}
        <div
          class="z-50 my-4 text-base list-none bg-white divide-y divide-gray-100 rounded-lg shadow dark:bg-gray-700 dark:divide-gray-600 my-drop"
        >
          <div class="px-4 py-3">
            <span class="block text-sm text-gray-900 dark:text-white"
              >{account.user.username}</span
            >
            <span
              class="block text-sm font-medium text-gray-500 truncate dark:text-gray-400"
              >{account.user.email}</span
            >
          </div>
          <ul class="py-2" aria-labelledby="user-menu-button">
            {#each options as option}
              <li>
                <a
                  href={option.link}
                  class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
                >
                  {option.name}
                </a>
              </li>
            {/each}
            <li>
              <span
                on:click={logout}
                style="cursor: pointer;"
                class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white"
              >
                Sair
              </span>
            </li>
          </ul>
        </div>
      {/if}

      <button
        data-testid="menu-hamburguer"
        on:click={toggleMenu}
        type="button"
        class="inline-flex items-center p-2 ml-1 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
      >
        <span class="sr-only">Open main menu</span>
        <svg
          class="w-6 h-6"
          aria-hidden="true"
          fill="currentColor"
          viewBox="0 0 20 20"
          xmlns="http://www.w3.org/2000/svg"
          ><path
            fill-rule="evenodd"
            d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
            clip-rule="evenodd"
          /></svg
        >
      </button>
    </div>
  </div>
</nav>

<style>
  .my-drop {
    position: absolute;
    right: 20px;
    top: 48px;
  }
</style>
