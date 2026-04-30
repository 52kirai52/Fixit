import { useState } from 'react'
import { login } from '../../api/auth'

function BeforeUser({ setUser }) {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')

  const handleLogin = async () => {
    try {
      const res = await login(username, password)
      localStorage.setItem('token', res.data.token)
      setUser({ username: res.data.username, shopName: res.data.shopName })
      setError('')
    } catch {
      setError('아이디 또는 비밀번호가 틀렸습니다')
    }
  }

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') handleLogin()
  }

  const inputStyle = {
    width: '100%', height: '22px', fontSize: '12px',
    padding: '0 6px', border: '2px inset #7f9db9',
    borderRadius: '2px', backgroundColor: 'white', boxSizing: 'border-box'
  }

  const btnDefault = {
    borderColor: '#ffffff #808080 #808080 #ffffff',
    boxShadow: '1px 1px 0px #000',
    transform: 'none'
  }

  const btnPressed = {
    borderColor: '#808080 #ffffff #ffffff #808080',
    boxShadow: 'none',
    transform: 'translate(1px, 1px)'
  }

  return (
    <div style={{ padding: '10px', display: 'flex', flexDirection: 'column', gap: '6px' }}>
      <div style={{ display: 'flex', gap: '10px' }}>
        <div style={{ display: 'flex', flexDirection: 'column', gap: '6px', flex: 1, minWidth: 0 }}>
          <input
            type="text"
            placeholder="아이디"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            onKeyDown={handleKeyDown}
            style={inputStyle}
          />
          <input
            type="password"
            placeholder="비밀번호"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            onKeyDown={handleKeyDown}
            style={inputStyle}
          />
          <div style={{ display: 'flex', gap: '8px', whiteSpace: 'nowrap' }}>
            <span
              style={{ fontSize: '11px', color: '#0054E3', cursor: 'pointer', textDecoration: 'underline' }}
              onMouseEnter={e => e.target.style.fontWeight = 'bold'}
              onMouseLeave={e => e.target.style.fontWeight = 'normal'}
            >회원가입</span>
            <span
              style={{ fontSize: '11px', color: '#0054E3', cursor: 'pointer', textDecoration: 'underline' }}
              onMouseEnter={e => e.target.style.fontWeight = 'bold'}
              onMouseLeave={e => e.target.style.fontWeight = 'normal'}
            >계정 찾기</span>
          </div>
        </div>
        <div style={{ display: 'flex', alignItems: 'flex-start' }}>
          <button
            onClick={handleLogin}
            style={{
              width: '48px', height: '50px', fontSize: '10px',
              backgroundColor: '#ECE9D8', border: '2px solid',
              borderRadius: '2px', cursor: 'pointer',
              letterSpacing: '3px', transition: 'all 0.05s',
              ...btnDefault
            }}
            onMouseDown={e => Object.assign(e.target.style, btnPressed)}
            onMouseUp={e => Object.assign(e.target.style, btnDefault)}
            onMouseLeave={e => Object.assign(e.target.style, btnDefault)}
          >
            로그인
          </button>
        </div>
      </div>
      {error && (
        <span style={{ fontSize: '11px', color: 'red', wordBreak: 'keep-all' }}>{error}</span>
      )}
    </div>
  )
}

export default BeforeUser