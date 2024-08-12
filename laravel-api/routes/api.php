<?php

use App\Http\Controllers\Api\SiswaController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;



Route::apiResource('/siswa', SiswaController::class);
Route::get('/carisiswa', [SiswaController::class, 'cari']);
