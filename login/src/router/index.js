import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/layout/LayOut'


Vue.use(Router)

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/LoginIndex'),
    hidden: true
  },
  
  {
    path:'/404',
    component:() => import('@/views/PageError'),
    hidden: true
  },
 
  {
    path: '/',
    component: Layout,
    redirect: '/mainpage',
    children: [{
      path: 'mainpage',
      name: 'Mainpage',
      component: () => import('@/views/mainpage/MainIndex'),
      meta: { title: '首页',  }//可以加入图片icon
    }]
  },
  
]

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()


export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // 路由重置
}


export default router
