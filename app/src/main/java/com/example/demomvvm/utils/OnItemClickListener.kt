package com.example.demomvvm.utils

import com.example.demomvvm.utils.Constants.POSITION_DEFAULT

interface OnItemClickListener<T> {
    fun onItemViewClick(item: T, position: Int = POSITION_DEFAULT)
}
