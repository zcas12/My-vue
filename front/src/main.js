import { createApp } from 'vue'
import App from './App.vue'
import router from './routes/index.js'
import store from './store/index'
import axios from "axios";
import VueAxios from 'vue-axios'
import VueCollapsiblePanel from '@dafcoe/vue-collapsible-panel'
import VueCookies from "vue3-cookies";

createApp(App)
    .use(router)
    .use(store)
    .use(VueAxios ,axios)
    .use(VueCollapsiblePanel)
    .use(VueCookies)
    .mount('#app')
