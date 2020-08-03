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
        path: '/Home',
        name: 'Home',
        component: () => import('@/views/Home.vue'),
        meta: {
            title: '数据大屏demo展示页'
        }
    },
    {
        path: '/line',
        name: 'dynamicLine',
        component: () => import('@/components/Screen/model.vue'),
        meta: {
            title: '折线图'
        }
    },
    {
        path: '/screen',
        name: 'Screen',
        component: () => import('@/views/Screen.vue'),
        meta: {
            title: '银行数据统计大屏'
        }
    }
]
const router = new Router({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes
})

export default router
