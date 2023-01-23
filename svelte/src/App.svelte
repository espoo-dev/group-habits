<script lang="ts">
  import { Router, links, Route } from 'svelte-routing';
  import Categories from './routes/pages/Categories.svelte';
  import Login from './routes/pages/Login.svelte';
</script>

<div use:links>
  <Router url="">
    <div>
      <Route path="/" component={Login} />
      <Route path="/categories" component={Categories} />
    </div>
  </Router>
</div>

<style>
  :global(body) {
    background-color: #f9f9f9;
    color: #000;
  }
</style>
