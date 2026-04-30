function AfterUser({ user, setUser }) {

  const handleLogout = () => {
    localStorage.removeItem('token')
    setUser(null)
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
    <div style={{ padding: '30px 10px', display: 'flex', flexDirection: 'column', gap: '16px', alignItems: 'center' }}>

        {/* 사업체명 */}
        <div style={{ fontSize: '11px', color: '#333', textAlign: 'center' }}>
        <span style={{ fontWeight: 'bold' }}>{user?.shopName ?? '사업체'}</span> 반갑습니다
        </div>

        {/* 버튼 */}
        <div style={{ display: 'flex', gap: '6px', width: '100%' }}>
        <button
            style={{
            flex: 1, height: '24px', fontSize: '11px',
            backgroundColor: '#ECE9D8', border: '2px solid',
            borderRadius: '2px', cursor: 'pointer',
            transition: 'all 0.05s', ...btnDefault
            }}
            onMouseDown={e => Object.assign(e.target.style, btnPressed)}
            onMouseUp={e => Object.assign(e.target.style, btnDefault)}
            onMouseLeave={e => Object.assign(e.target.style, btnDefault)}
        >설정</button>
        <button
            onClick={handleLogout}
            style={{
            flex: 1, height: '24px', fontSize: '11px',
            backgroundColor: '#ECE9D8', border: '2px solid',
            borderRadius: '2px', cursor: 'pointer',
            transition: 'all 0.05s', ...btnDefault
            }}
            onMouseDown={e => Object.assign(e.target.style, btnPressed)}
            onMouseUp={e => Object.assign(e.target.style, btnDefault)}
            onMouseLeave={e => Object.assign(e.target.style, btnDefault)}
        >로그아웃</button>
        </div>

    </div>
    )
}

export default AfterUser