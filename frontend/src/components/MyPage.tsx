import {useState} from "react";
import {IoMenu} from "react-icons/io5";
import {MdAccountCircle} from "react-icons/md";
import {Link} from "react-router-dom";

function MyPage() {
    const [menuOpen, setMenuOpen] = useState(false);
    const toggleMenu = () => setMenuOpen(!menuOpen);

    return (
        <div className="min-h-screen bg-gray-100">
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
                                <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">ログアウト</li>
                            </ul>
                        </div>
                    )}
                </div>
            </header>
        </div>
    );
}

export default MyPage;