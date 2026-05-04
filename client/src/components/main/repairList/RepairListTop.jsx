function RepairListTop({ onOpenForm }) {
  return (
    <div style={{
      height: '160px',
      borderBottom: '1px solid #c0d0e0',
      display: 'flex',
      alignItems: 'flex-start',
      justifyContent: 'flex-end',
      padding: '12px'
    }}>
      <button
        onClick={onOpenForm}
        style={{
          height: '28px',
          padding: '0 12px',
          fontSize: '12px',
          backgroundColor: '#ECE9D8',
          border: '2px solid',
          borderColor: '#ffffff #808080 #808080 #ffffff',
          boxShadow: '1px 1px 0px #000',
          borderRadius: '2px',
          cursor: 'pointer'
        }}
        onMouseDown={e => Object.assign(e.currentTarget.style, {
          borderColor: '#808080 #ffffff #ffffff #808080',
          boxShadow: 'none',
          transform: 'translate(1px, 1px)'
        })}
        onMouseUp={e => Object.assign(e.currentTarget.style, {
          borderColor: '#ffffff #808080 #808080 #ffffff',
          boxShadow: '1px 1px 0px #000',
          transform: 'none'
        })}
        onMouseLeave={e => Object.assign(e.currentTarget.style, {
          borderColor: '#ffffff #808080 #808080 #ffffff',
          boxShadow: '1px 1px 0px #000',
          transform: 'none'
        })}
      >
        정비 등록
      </button>
    </div>
  )
}

export default RepairListTop