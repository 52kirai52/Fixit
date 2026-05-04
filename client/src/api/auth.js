import api from './axios'

export const login = (username, password) => {
    return api.post('/api/users/login', { username, password })
}

export const register = (username, password, name, phone, shopName, shopAddress) => {
    return api.post('/api/auth/register', { username, password, name, phone, shopName, shopAddress })
}

// 로그인한 사용자 정보 조회
export const getMe = () => {
    return api.get('/api/users/me')
}