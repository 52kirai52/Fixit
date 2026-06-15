import { useState, useEffect } from 'react'
import { getMe } from '../api/auth'
import { getToken, removeToken } from '../utils/token'

// 역할: 앱 시작 시 로그인 상태 복원
// 사용: App.jsx
export const useAuth = () => {
  // 토큰 있으면 undefined(확인중), 없으면 null(비로그인)
  const [user, setUser] = useState(() => {
    const token = getToken()
    return token ? undefined : null
  })

  // 앱 시작 시 딱 한 번 토큰 검증
  useEffect(() => {
    const token = getToken()
    if (token) {
      getMe()
        .then(({ data }) => {
          setUser({ username: data.username, shopName: data.shopName })
        })
        .catch(() => {
          removeToken()
          setUser(null)
        })
    }
  }, [])

  return { user, setUser }
}