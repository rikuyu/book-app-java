import {useState} from 'react';
import {MdAccountCircle} from 'react-icons/md';
import {IoMenu} from 'react-icons/io5';
import {Link} from 'react-router-dom';
import {useLogout} from "../utils/Logout.ts";

function MyPage() {
    const [menuOpen, setMenuOpen] = useState(false);
    const toggleMenu = () => setMenuOpen(!menuOpen);

    const logout = useLogout();

    const [userData] = useState({
        id: 1,
        username: 'JohnDoe',
        email: 'john.doe@example.com',
    });

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col">
            <header className="bg-green-600 text-white py-4 flex justify-between items-center px-5">
                <div className="flex items-center">
                    <MdAccountCircle className="text-white w-8 h-8 mr-2"/>
                    <h1 className="text-2xl font-medium text-left">マイページ</h1>
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
                <div className="bg-white p-8 rounded-lg shadow-md max-w-md w-full">
                    <h2 className="text-xl font-bold mb-6">ユーザー情報</h2>
                    <div className="mb-8 border-b pb-1 border-black">
                        <span className="font-medium text-gray-700">id:</span>
                        <span className="ml-2">{userData.id}</span>
                    </div>
                    <div className="mb-8 border-b pb-1 border-black">
                        <span className="font-medium text-gray-700">ユーザー名:</span>
                        <span className="ml-2">{userData.username}</span>
                    </div>
                    <div className="mb-4 border-b pb-1 border-black">
                        <span className="font-medium text-gray-700">メールアドレス:</span>
                        <span className="ml-2">{userData.email}</span>
                    </div>
                </div>
            </main>
        </div>

    );
}

export default MyPage;
