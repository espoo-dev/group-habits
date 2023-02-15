import { defineConfig, loadEnv } from 'vite';
import { svelte } from '@sveltejs/vite-plugin-svelte';
import postcss from './postcss.config.js';

// https://vitejs.dev/config/
export default () => { 
    return defineConfig({
      css: {
        postcss,
      },
      plugins: [
        svelte({
          /* plugin options */
        }),
      ],
    });
}