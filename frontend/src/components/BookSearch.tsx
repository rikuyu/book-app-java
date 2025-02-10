import React, {useState} from 'react';
import { FaSearch } from "react-icons/fa";
import { IoMenu } from "react-icons/io5";

const BookSearch: React.FC = () => {
    const [menuOpen, setMenuOpen] = useState(false);
    const toggleMenu = () => setMenuOpen(!menuOpen);

    return (
        <div className="min-h-screen bg-gray-100">
            <header className="bg-green-600 text-white py-4 flex justify-between items-center px-5">
                <h1 className="text-2xl font-medium text-left">図書館の書籍検索</h1>
                <div className="relative">
                    <button
                        onClick={toggleMenu}
                        className="p-2 text-white rounded-full focus:outline-none"
                    >
                        <IoMenu className="text-white mx-3 w-6 h-6"/>
                    </button>
                    {menuOpen && (
                        <div className="absolute right-0 mt-2 w-48 bg-white rounded shadow-lg z-10">
                            <ul className="text-gray-800">
                                <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">すべての書籍</li>
                                <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">人気の書籍</li>
                                <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">マイページ</li>
                                <li className="hover:bg-gray-100 px-5 py-4 cursor-pointer">ログアウト</li>
                            </ul>
                        </div>
                    )}
                </div>
            </header>
            <div className="px-20 py-4">
                <div className="flex items-center border border-gray-400 rounded-lg overflow-hidden">
                    <FaSearch className="text-gray-500 mx-3 w-5 h-5" />
                    <input
                        type="text"
                        placeholder="書籍を検索..."
                        className="w-full py-4 px-1 focus:outline-none"
                    />
                </div>
            </div>
        </div>
    );
};

export default BookSearch;