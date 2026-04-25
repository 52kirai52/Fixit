import { useState } from 'react'
import { register } from '../api/auth'
import { useNavigate } from 'react-router-dom'

function RegisterPage() {
    const navigate = useNavigate()
    const [step, setStep] = useState(1)
    const [userData, setUserData] = useState({
        username: '',
        password: '',
        name: '',
        phone: ''
    })
    const [shopData, setShopData] = useState({
        name: '',
        address: ''
    })
    const [error, setError] = useState('')

    const handleNext = (e) => {
        e.preventDefault()
        setError('')
        setStep(2)
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        setError('')
        try {
            await register(
                userData.username,
                userData.password,
                userData.name,
                userData.phone,
                shopData.name,
                shopData.address
            )
            alert('가입 완료! 로그인해주세요.')
            navigate('/login')
        } catch (err) {
            setError(err.response?.data || '오류가 발생했습니다.')
        }
    }

    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-100">
            <div className="bg-white p-8 rounded-lg shadow-md w-96">

                {/* 단계 표시 */}
                <div className="flex items-center justify-center mb-6 gap-2">
                    <div className={`w-8 h-8 rounded-full flex items-center justify-center text-sm font-bold
                        ${step === 1 ? 'bg-blue-600 text-white' : 'bg-blue-200 text-blue-600'}`}>
                        1
                    </div>
                    <div className="w-8 h-px bg-gray-300" />
                    <div className={`w-8 h-8 rounded-full flex items-center justify-center text-sm font-bold
                        ${step === 2 ? 'bg-blue-600 text-white' : 'bg-gray-200 text-gray-400'}`}>
                        2
                    </div>
                </div>

                <h1 className="text-2xl font-bold text-center mb-6">
                    {step === 1 ? '계정 정보' : '사업체 정보'}
                </h1>

                {/* 1단계 */}
                {step === 1 && (
                    <form onSubmit={handleNext}>
                        <div className="mb-4">
                            <input
                                type="text"
                                placeholder="아이디"
                                value={userData.username}
                                onChange={(e) => setUserData({ ...userData, username: e.target.value })}
                                className="w-full border p-2 rounded"
                            />
                        </div>
                        <div className="mb-4">
                            <input
                                type="password"
                                placeholder="비밀번호"
                                value={userData.password}
                                onChange={(e) => setUserData({ ...userData, password: e.target.value })}
                                className="w-full border p-2 rounded"
                            />
                        </div>
                        <div className="mb-4">
                            <input
                                type="text"
                                placeholder="이름"
                                value={userData.name}
                                onChange={(e) => setUserData({ ...userData, name: e.target.value })}
                                className="w-full border p-2 rounded"
                            />
                        </div>
                        <div className="mb-4">
                            <input
                                type="text"
                                placeholder="연락처"
                                value={userData.phone}
                                onChange={(e) => setUserData({ ...userData, phone: e.target.value })}
                                className="w-full border p-2 rounded"
                            />
                        </div>
                        <button
                            type="submit"
                            className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700"
                        >
                            다음
                        </button>
                        <p className="text-center text-gray-400 text-xs mt-4">
                            사업체 등록까지 완료해야 로그인이 가능합니다
                        </p>
                    </form>
                )}

                {/* 2단계 */}
                {step === 2 && (
                    <form onSubmit={handleSubmit}>
                        <div className="mb-4">
                            <input
                                type="text"
                                placeholder="사업체명"
                                value={shopData.name}
                                onChange={(e) => setShopData({ ...shopData, name: e.target.value })}
                                className="w-full border p-2 rounded"
                            />
                        </div>
                        <div className="mb-4">
                            <input
                                type="text"
                                placeholder="주소 (선택)"
                                value={shopData.address}
                                onChange={(e) => setShopData({ ...shopData, address: e.target.value })}
                                className="w-full border p-2 rounded"
                            />
                        </div>
                        {error && <p className="text-red-500 text-sm mb-4">{error}</p>}
                        <button
                            type="submit"
                            className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700"
                        >
                            가입 완료
                        </button>
                        <p className="text-center text-gray-400 text-xs mt-4">
                            사업체 등록까지 완료해야 로그인이 가능합니다
                        </p>
                    </form>
                )}

            </div>
        </div>
    )
}

export default RegisterPage