import request from '@/utils/request'


export function taskList(data) {
  return request({
    //url: '/user/login',
    url: '/mdm/main/taskList',
    method: 'get',
    data
  });
}
export function taskListDone(id) {
  return request({
        url: '/mdm/main/taskListDone',
    method: 'get'
  });
}
