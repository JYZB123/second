import Vue from 'vue'
import VueRouter from 'vue-router'
import Student from '../views/Student'
import Teacher from '../views/Teacher'
import Home from '../views/Home'
import Login from '../views/Login'
import Course from "../views/Course";
import CourseHome from "../views/CourseHome";
import Course_Introduction from "../views/Course_Introduction";
import Course_Team from "../views/Course_Team";

Vue.use(VueRouter);

const routes = [
  {
    path: '/team',
    name: 'Course_Team',
    component: Course_Team,
    hidden: true,
  },

  {
    path: '/introduction',
    name: 'Course_Introduction',
    component: Course_Introduction,
    hidden: true,
  },
  {
    path: '/courseHome',
    name: 'CourseHome',
    component: CourseHome,
    hidden: true,
  },
  {
    path: '/',
    name: 'Login',
    component: Login,
    hidden: true
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    hidden: true
  },
  {
    path: '/home',
    name: '导航一',
    component: Home,
    children:[
      {
        path: '/student',
        name: '管理学生',
        component: Student
      },
      {
        path: '/teacher',
        name: '管理教师',
        component: Teacher
      },
      {
        path: '/course',
        name: '管理课程',
        component: Course
      }
    ]
  },
]

const router = new VueRouter({
  routes
})

export default router
