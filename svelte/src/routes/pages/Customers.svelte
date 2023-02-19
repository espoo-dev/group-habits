<script lang="ts">
  import Popover from '../../lib/components/Popover/Popover.svelte';
  import Modal from '../../lib/components/Modal/Modal.svelte';
  import { notifications } from '../../../src/infra/notification/notification';
  import { makeRemoteCustomer } from '../../main/factories/usecases/remote-customer-factory';
  import Menu from '../../lib/components/Menu/Menu.svelte';
  import type { CustomerModel } from 'src/domain/models/customer-model';
  import Input from '../../lib/components/Input/Input.svelte';

  const crudName = 'Cliente'
  let customers = [];
  let defaultCustomer: CustomerModel = {
    id: 0,
    name: '',
    document_number: 0,
    phone: 0,
    state_inscription: '',
    customer_type: 'business'
  };

  let modal = {
    title: `Adicionar ${crudName}`,
    editTitle: `Editar ${crudName}`,
    model: {...defaultCustomer},
    new: true,
    opened: false
  }

  const apiCustomer = makeRemoteCustomer();
  const load = async () => {
    customers = await apiCustomer.list();
    customers = customers.map((customer) => {
      return {
        ...customer,
        popRemove: false
      }
    })
  }

  const closeModal = () => {
    modal.model = {...defaultCustomer}
    modal.opened = false
    modal.new = true
  }

  const create = async () => {
    try {
      await apiCustomer.create(modal.model)
      notifications.success(`${crudName} criado com sucesso`)
      closeModal()
      load()
    } catch (error) {
      notifications.danger(error)
    }
  }

  const edit = async () => {
    try {
      await apiCustomer.edit(modal.model.id, modal.model)
      closeModal()
      load()
      notifications.success(`${crudName} editado com sucesso`)
    } catch (error) {
      notifications.danger(error)
    }
  }

  const remove = async (customer) => {
    try {
      await apiCustomer.delete(customer.id)
      customer.popRemove = false
      notifications.success(`${crudName} removido com sucesso`)
      load()
    } catch (error) {
      notifications.danger(error)
    }
  }

  const openModalToEdit = (customer: CustomerModel) => {
    modal.opened = true
    modal.new = false
    modal.model = customer
  }

  load();
</script>

<Menu />
<div class="page-container">
  <div class="header-page">
    <h1
      class="mb-4 text-3xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl"
    >
      <span
        class="text-blue-600 dark:text-blue-500"
        >Clientes</span
      >
    </h1>

    <button on:click={() => modal.opened = true} class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">
      Novo
    </button>
  </div>

  <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
    <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
      <thead
        class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400"
      >
        <tr>
          <th scope="col" class="px-6 py-3"> Código </th>
          <th scope="col" class="px-6 py-3"> Nome </th>
          <th scope="col" class="px-6 py-3"> Tipo </th>
          <th scope="col" class="px-6 py-3"> Telefone </th>
        </tr>
      </thead>
      <tbody>
        {#each customers as customer}
          <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
            <th
              scope="row"
              class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
            >
              {customer.id}
            </th>
            <td class="px-6 py-4"> {customer.name} </td>
            <td class="px-6 py-4"> {customer.customer_type} </td>
            <td class="px-6 py-4"> {customer.phone} </td>
            <td class="px-6 py-4">
              <button
                on:click={() => openModalToEdit(customer)}
                type="button"
                class="text-blue-700 hover:text-white border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:border-blue-500 dark:text-blue-500 dark:hover:text-white dark:hover:bg-blue-600 dark:focus:ring-blue-800">
                Editar
              </button>

              <button
                on:click={() => customer.popRemove = true}
                type="button"
                class="text-red-700 hover:text-white border border-red-700 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:border-red-500 dark:text-red-500 dark:hover:text-white dark:hover:bg-red-600 dark:focus:ring-red-900">
                Remover
              </button>

              {#if customer.popRemove}
                <Popover
                  message={`Tem certeza que deseja excluir o cliente ${customer.name}`}
                  on:close={() => customer.popRemove = false}
                  on:confirm={() => remove(customer)}
                  >
                </Popover>
              {/if}
            </td>
          </tr>
        {/each}
      </tbody>
    </table>
  </div>

  {#if modal.opened}
    <Modal
      title={modal.new ? modal.title : modal.editTitle}
      on:close="{() => closeModal()}"
      on:confirm="{() => modal.new ? create() : edit()}"
      >
      <form>
        <div class="grid gap-6 mb-6 md:grid-cols-1">
          <Input 
            label='Nome'
            bind:value={modal.model.name}
          />
          <Input 
            label='Documento'
            bind:value={modal.model.document_number} 
            type="number"
          />
          <Input 
            label='Telefone'
            bind:value={modal.model.phone} 
            type="number"
          />

          <h3 class="mb-4 font-semibold text-gray-900 dark:text-white">Tipo do cliente</h3>
          <ul class="items-center w-full text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex dark:bg-gray-700 dark:border-gray-600 dark:text-white">
            <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
              <div class="flex items-center pl-3">
                <input id="horizontal-list-radio-license" bind:group={modal.model.customer_type} type="radio" value={'business'} name="list-radio" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500">
                <label for="horizontal-list-radio-license" class="w-full py-3 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Business</label>
              </div>
            </li>
            <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
              <div class="flex items-center pl-3">
                <input id="horizontal-list-radio-id" bind:group={modal.model.customer_type} type="radio" value={'person'} name="list-radio" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500">
                <label for="horizontal-list-radio-id" class="w-full py-3 ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Person</label>
              </div>
            </li>
          </ul>

          {#if modal.model.customer_type === 'business'}
            <Input 
              label='Inscrição Estadual'
              bind:value={modal.model.state_inscription} 
              type="text"
            />
          {/if}
        </div>
      </form>
    </Modal>
  {/if}

</div>

<style>
  .page-container {
    padding: 24px;
  }

  .header-page {
    display: flex;
    justify-content: space-between;
    align-items: center;
    
    margin-bottom: 46px;
  }
</style>
