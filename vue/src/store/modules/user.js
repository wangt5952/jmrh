import {
  login,
  logout,
  getInfo
} from '@/api/login'
import {
  getToken,
  setToken,
  removeToken
} from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: '',
    message: ''
  },

  mutations: {//对传入的数据仅进行操作
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_MES: (state, message) => {
      state.message = message
    }
  },

  actions: {//可以对数据进行异步操作
    // 登录
    Login({
      commit
    }, userInfo) {
      // const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(userInfo).then(response => {
          const data = response.data
          if (response.success) {
            setToken(data.token)
            commit('SET_TOKEN', data.token)
            commit('SET_NAME', data.userName)
            commit('SET_ROLES', data.userType)
            window.sessionStorage.setItem('token',data.token)
            window.sessionStorage.setItem('checkStatus',data.checkStatus)
            window.sessionStorage.setItem('userName', data.userName)
            window.sessionStorage.setItem('name', data.name)
            window.sessionStorage.setItem('code', data.code)
            window.sessionStorage.setItem('userId', data.userId)
            window.sessionStorage.setItem('userType', data.userType)
            window.sessionStorage.setItem('cellphone', data.cellphone)
            window.sessionStorage.setItem('email', data.email)
            window.sessionStorage.setItem('orgType', data.orgType)
          } else {
            commit('SET_MES', response.message)
          }
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({
      commit,
      state
    }) {
      return new Promise((resolve, reject) => {
        getInfo(state.token).then(response => {
          const data = response.data
          commit('SET_ROLES', data.role)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    LogOut({
      commit,
      state
    }) {
      return new Promise((resolve, reject) => {

        logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resolve()
        window.sessionStorage.removeItem('token')
        window.localStorage.removeItem('user')
        window.sessionStorage.removeItem('treeData') //必须传入 路由进行渲染
        window.sessionStorage.removeItem('isLoadNodes')
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({
      commit
    }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
        window.sessionStorage.removeItem('user')
        window.sessionStorage.removeItem('isLoadNodes')
      })
    }
  }
}

export default user
