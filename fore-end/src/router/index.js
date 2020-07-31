import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [
    {
        path: '/',
        name: 'HelloWorld',
        component: () => import('@/components/HelloWorld/index.vue'),
        meta: {
            title: 'vue首页'
        }
    },
    {
        path: '/home',
        name: 'Home',
        component: () => import('@/views/Home.vue'),
        meta: {
            title: '数据大屏展示页'
        }
    },
    {
        path: '/line',
        name: 'seamless',
        component: () => import('@/components/seamless/index.vue'),
        meta: {
            title: '折线图'
        }
    }
]
const router = new Router({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

export default router
