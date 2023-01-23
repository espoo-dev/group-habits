import App from './App.svelte';
import './index.css';
import '@fortawesome/fontawesome-free/css/all.css';
import Login from './routes/pages/Login.svelte';

const app = new Login({
  target: document.getElementById('app'),
});

export default app;
