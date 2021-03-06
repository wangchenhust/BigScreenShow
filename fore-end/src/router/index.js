import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routes = [
    {
        path: '/config',
        name: 'config',
        component: () => import('@/components/config/index.vue'),
        meta: {
            title: '配置页'
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
        component: () => import('@/components/Screen/resume.vue'),
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
