import { createRouter, createWebHashHistory } from "vue-router";
import Home from './Home'
import Interior from './Interior'
import Kitchen from './Kitchen'
import Bedroom from './Bedroom'
import About from './About'
import QnA from './QnA'
import QnAWrite from './QnAWrite'
import ItemDetail from "~/routes/ItemDetail";
import Login from "~/routes/Login";
import SignUp from "./SignUp";
import MyPage from "./MyPage";
import WishList from "../components/WishList";
import OrderList from "../components/OrderList";
import MyInfo from "../components/MyInfo";
import AdminPage from "./AdminPage";
import Admin_OrderList from "../components/Admin_OrderList";
import Admin_MemberList from "../components/Admin_MemberList";
import Admin_Item from "../components/Admin_Item";
export default createRouter ({
    //Hash
    // https://google.com/#/search
    history:createWebHashHistory(),
    // pages
    routes: [
        {
            path:'/',
            component: Home
        },
        {
            path:'/interior',
            component: Interior
        },
        {
            path:'/kitchen',
            component: Kitchen
        },
        {
            path:'/bedroom',
            component: Bedroom
        },
        {
            path:'/about',
            component: About
        },
        {
            path:'/itemdetail/:id',
            component: ItemDetail
        },
        {
            path:'/QnA',
            component: QnA
        },
        {
            path:'/QnAWrite',
            component: QnAWrite
        },
        {
            path:'/Login',
            component: Login
        },
        {
            path:'/SignUp',
            component: SignUp
        },
        {
            path:'/myPage',
            component: MyPage
        },
        {
            path:'/WishList',
            component: WishList
        },
        {
            path:'/OrderList',
            component: OrderList
        },
        {
            path:'/MyInfo',
            component: MyInfo
        },
        {
            path:'/AdminPage',
            component: AdminPage
        },
        {
            path:'/Admin_OrderList',
            component: Admin_OrderList
        },
        {
            path:'/Admin_MemberList',
            component: Admin_MemberList
        },
        {
            path:'/Admin_Item',
            component: Admin_Item
        }
    ]

})