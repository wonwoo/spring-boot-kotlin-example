package com.example

import org.mockito.Mockito

inline fun <reified T> any(): T = Mockito.any()
