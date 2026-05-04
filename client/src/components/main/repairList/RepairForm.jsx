import { useState, useRef } from 'react'
import { registerRepair } from '../../../api/repairs'

function RepairForm({ onClose }) {
  const [pos, setPos] = useState({ x: 0, y: 0 })
  const dragStart = useRef(null)

  const [form, setForm] = useState({
    customerName: '',
    plateNumber: '',
    customerPhone: '',
    memo: ''
  })

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const handleSubmit = async () => {
    if (!form.customerName || !form.plateNumber) return
    try {
      await registerRepair(form)
      onClose()
    } catch (e) {
      console.error(e)
    }
  }

  const handleMouseDown = (e) => {
    dragStart.current = {
      mouseX: e.clientX,
      mouseY: e.clientY,
      posX: pos.x,
      posY: pos.y
    }
    window.addEventListener('mousemove', handleMouseMove)
    window.addEventListener('mouseup', handleMouseUp)
  }

  const handleMouseMove = (e) => {
    if (!dragStart.current) return
    setPos({
      x: dragStart.current.posX + e.clientX - dragStart.current.mouseX,
      y: dragStart.current.posY + e.clientY - dragStart.current.mouseY
    })
  }

  const handleMouseUp = () => {
    dragStart.current = null
    window.removeEventListener('mousemove', handleMouseMove)
    window.removeEventListener('mouseup', handleMouseUp)
  }

  const inputStyle = {
    width: '100%',
    height: '24px',
    fontSize: '12px',
    padding: '0 6px',
    border: '2px inset #7f9db9',
    borderRadius: '2px',
    backgroundColor: 'white',
    boxSizing: 'border-box'
  }

  const labelStyle = {
    fontSize: '12px',
    marginBottom: '4px'
  }

  const fieldStyle = {
    display: 'flex',
    flexDirection: 'column',
    gap: '4px'
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
    <div style={{
      position: 'absolute',
      top: `calc(50% + ${pos.y}px)`,
      left: `calc(50% + ${pos.x}px)`,
      transform: 'translate(-50%, -50%)',
      width: '320px',
      backgroundColor: '#ECE9D8',
      border: '2px solid #0054E3',
      borderRadius: '8px 8px 0 0',
      boxShadow: '4px 4px 8px rgba(0,0,0,0.4)',
      zIndex: 100
    }}>

      {/* 타이틀바 - 드래그 핸들 */}
      <div
        onMouseDown={handleMouseDown}
        style={{
          background: 'linear-gradient(to bottom, #0054E3, #0831D9)',
          height: '28px',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between',
          paddingLeft: '8px',
          paddingRight: '4px',
          userSelect: 'none'
        }}>
        <span style={{ color: 'white', fontSize: '12px', fontWeight: 'bold' }}>정비 등록</span>
        <button
          onClick={onClose}
          style={{
            width: '16px', height: '16px', fontSize: '10px',
            backgroundColor: '#ECE9D8', border: '2px solid',
            borderRadius: '2px', cursor: 'pointer', lineHeight: 1,
            ...btnDefault
          }}
          onMouseDown={e => { e.stopPropagation(); Object.assign(e.target.style, btnPressed) }}
          onMouseUp={e => Object.assign(e.target.style, btnDefault)}
          onMouseLeave={e => Object.assign(e.target.style, btnDefault)}
        >✕</button>
      </div>

      {/* 폼 내용 */}
      <div style={{ padding: '16px', display: 'flex', flexDirection: 'column', gap: '12px' }}>

        <div style={fieldStyle}>
          <span style={labelStyle}>고객명 *</span>
          <input type="text" name="customerName" value={form.customerName} onChange={handleChange} placeholder="고객명" style={inputStyle} />
        </div>

        <div style={fieldStyle}>
          <span style={labelStyle}>차량번호 *</span>
          <input type="text" name="plateNumber" value={form.plateNumber} onChange={handleChange} placeholder="12가3456" style={inputStyle} />
        </div>

        <div style={fieldStyle}>
          <span style={labelStyle}>연락처</span>
          <input type="text" name="customerPhone" value={form.customerPhone} onChange={handleChange} placeholder="010-0000-0000" style={inputStyle} />
        </div>

        <div style={fieldStyle}>
          <span style={labelStyle}>메모</span>
          <textarea
            name="memo"
            value={form.memo}
            onChange={handleChange}
            placeholder="메모"
            style={{
              ...inputStyle,
              height: '60px',
              padding: '4px 6px',
              resize: 'none'
            }}
          />
        </div>

        <div style={{ display: 'flex', justifyContent: 'flex-end', gap: '6px' }}>
          <button
            onClick={handleSubmit}
            style={{
              height: '24px', padding: '0 12px', fontSize: '12px',
              backgroundColor: '#ECE9D8', border: '2px solid',
              borderRadius: '2px', cursor: 'pointer', ...btnDefault
            }}
            onMouseDown={e => Object.assign(e.target.style, btnPressed)}
            onMouseUp={e => Object.assign(e.target.style, btnDefault)}
            onMouseLeave={e => Object.assign(e.target.style, btnDefault)}
          >등록</button>
          <button
            onClick={onClose}
            style={{
              height: '24px', padding: '0 12px', fontSize: '12px',
              backgroundColor: '#ECE9D8', border: '2px solid',
              borderRadius: '2px', cursor: 'pointer', ...btnDefault
            }}
            onMouseDown={e => Object.assign(e.target.style, btnPressed)}
            onMouseUp={e => Object.assign(e.target.style, btnDefault)}
            onMouseLeave={e => Object.assign(e.target.style, btnDefault)}
          >취소</button>
        </div>

      </div>
    </div>
  )
}

export default RepairForm