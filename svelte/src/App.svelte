<script lang="ts">
  import { Router, links, Route } from 'svelte-routing';
  import Toast from './lib/components/Toast/Toast.svelte';
  import Categories from './routes/pages/Categories.svelte';
  import Customers from './routes/pages/Customers.svelte';
  import Items from './routes/pages/Items.svelte';
  import Login from './routes/pages/Login.svelte';
</script>

<div use:links>
  <Router url="">
    <div>
      <Route path="/" component={Login} />
      <Route path="/categories" component={Categories} />
      <Route path="/customers" component={Customers} />
      <Route path="/items" component={Items} />
    </div>
  </Router>
  <Toast />
</div>

<style>
  :global(body) {
    background-color: #f9f9f9;
    color: #000;
  }
</style>
