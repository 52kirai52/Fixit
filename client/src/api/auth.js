import api from './axios'

export const login = (username, password) => {
    return api.post('/api/users/login', { username, password })
}

export const register = (data) => {
    return api.post('/api/users/register', data)
}