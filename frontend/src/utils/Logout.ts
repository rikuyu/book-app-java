import {useNavigate} from 'react-router-dom';

export const useLogout = () => {
    const navigate = useNavigate();

    const logout = () => {
        fetch('http://localhost:8080/logout', {
            method: 'POST',
            credentials: 'include',
        })
            .then((response) => {
                if (response.ok) {
                    navigate('/login');
                } else {
                    alert('ログアウトに失敗しました。');
                }
            })
            .catch((error) => {
                alert('ネットワークエラーが発生しました。');
                console.error('Logout error:', error);
            });
    };

    return logout;
};

