import {useEffect, useState} from 'react';
import {MdAccountCircle} from 'react-icons/md';
import {IoMenu} from 'react-icons/io5';
import {Link} from 'react-router-dom';
import {useLogout} from "../utils/Logout.ts";

function MyPage() {
    const [menuOpen, setMenuOpen] = useState(false);
    const toggleMenu = () => setMenuOpen(!menuOpen);
    const logout = useLogout();
    const [userData, setUserData] = useState({
        id: '',
        name: '',
        email: '',
        isAdmin: false,
    });

    useEffect(() => {
        fetchMe()
    }, []);

    const fetchMe = () => {
        fetch('http://localhost:8080/user/me', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: "include",
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Failed to fetch user data');
                }
                return response.json();
            })
            .then((data) => {
                setUserData({
                    id: data.id,
                    name: data.name,
                    email: data.email,
                    isAdmin: data.isAdmin,
                });
            })
            .catch((error) => {
                console.error('Error fetching user data:', error);
            });
    }

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col">
            <header className="bg-green-600 text-white py-4 flex justify-between items-center px-5">
                <div className="flex items-center">
                    <div className="flex items-center">
                        <MdAccountCircle className="text-white w-7 h-7 mr-2"/>
                        <h1 className="text-2xl font-medium text-left">マイページ</h1>
                    </div>
                    {userData.isAdmin && (
                        <>
                            <div className="border-l-2 border-white h-6 mx-4"></div>
                            <Link to="/admin/users" className="text-lg font-medium hover:underline">
                                管理用 すべてのユーザー
                            </Link>
                            <div className="border-l-2 border-white h-6 mx-4"></div>
                            <Link to="/admin/borrow_records" className="text-lg font-medium hover:underline">
                                管理用 貸出記録一覧
                            </Link>
                        </>
                    )}
                </div>
                <div className="relative">
                    <button
                        onClick={toggleMenu}
                        className="p-2 text-white rounded-full focus:outline-none"
                    >
                        <IoMenu className="text-white mx-3 w-7 h-7"/>
                    </button>
                    {menuOpen && (
                        <div className="absolute right-0 mt-2 w-48 bg-white rounded shadow-lg z-10">
                            <ul className="text-gray-800">
                                <Link to="/book">
                                    <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">すべての書籍</li>
                                </Link>
                                <Link to="/popular">
                                    <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">人気の書籍</li>
                                </Link>
                                <Link to="/search">
                                    <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">書籍の検索</li>
                                </Link>
                                <li
                                    className="hover:bg-gray-100 px-5 py-4 cursor-pointer"
                                    onClick={logout}>
                                    ログアウト
                                </li>
                            </ul>
                        </div>
                    )}
                </div>
            </header>

            <main className="flex-grow flex items-center justify-center">
                <div className="bg-white p-10 rounded-lg shadow-md max-w-lg w-full">
                    <div className="flex items-center mb-6">
                        <h2 className="text-2xl font-bold">ユーザー情報</h2>
                        {userData.isAdmin && (
                            <span className="ml-4 bg-red-500 text-white text-sm font-semibold py-1 px-2 rounded-md">
                              管理者
                            </span>
                        )}
                    </div>
                    <div className="mb-8 border-b pb-1 border-black">
                        <span className="font-medium text-gray-700 text-xl">id:</span>
                        <span className="ml-2 text-xl">{userData.id}</span>
                    </div>
                    <div className="mb-8 border-b pb-1 border-black">
                        <span className="font-medium text-gray-700 text-xl">ユーザー名:</span>
                        <span className="ml-2 text-xl">{userData.name}</span>
                    </div>
                    <div className="mb-3 border-b pb-1 border-black">
                        <span className="font-medium text-gray-700 text-xl">メールアドレス:</span>
                        <span className="ml-2 text-xl">{userData.email}</span>
                    </div>
                </div>
            </main>
        </div>

    );
}

export default MyPage;
