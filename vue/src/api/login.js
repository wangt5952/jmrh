import request from '@/utils/request'

// export function login(username, password) {
//   return request({
//     url: '/user/login',
//     method: 'post',
//     data: {
//       username,
//       password
//     }
//   })
// }
export function login(userName, userPassword) {
  return request({
    //url: '/user/login',
    url: '/mdm/user/signIn',
    method: 'post',
    data: {
      userName,
      userPassword
    }
  });
}
export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: {
      token
    }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
