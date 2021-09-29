import { createStore } from "vuex";
import item from './item';
import about from './about';
import member from "./member";

export default createStore({
    modules:{
        item,
        about,
        member
    }
})