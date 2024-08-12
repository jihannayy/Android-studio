<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Resources\SiswaResource;
use App\Models\Siswa;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class SiswaController extends Controller
{
    public function index()
    {
        $siswa = Siswa::all();

        return new SiswaResource(true, 'List Data Siswa', $siswa);
    }

    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'namasiswa' => 'required|string|max:255', 
            'nisn' => 'required|string|max:10|unique:siswas', 
            'alamat' => 'required|string|max:255',
            'gender' => 'required|string|max:255',
            'agama' => 'required|string|max:11',
            'usia' => 'required|string|max:3',
            'image' => 'sometimes|image|mimes:jpeg,png,jpg,gif,svg|max:2048',
        ], [
            'namasiswa.required' => "Nama harus diisi",
            'nisn.required' => "NISN harus diisi",
            'nisn.unique' => "NISN tidak boleh sama",
            'nisn.max' => "NISN tidak boleh lebih dari 10 digit",
            'alamat.required' => "Alamat harus diisi",
            'gender.required' => "Gender harus diisi",
            'agama.required' => "Agama harus diisi",
            'usia.required' => "Usia harus diisi",
            'usia.max' => "Usia tidak lebih dari 3 digit",
        ]);

        if ($validator->fails()) {
            return response()->json([
                'error' => true,
                'success' => 0,
                'message' => $validator->errors()->first(),
            ], 400); 
        }

        // if ($request->file('image')) {
        //     $image = $request->file('image');
        //     $image->storeAs('public/siswa', $image->hashName());
        // }



        $siswa = new Siswa;
        $siswa->namasiswa = $request->namasiswa;
        $siswa->nisn = $request->nisn;
        $siswa->alamat = $request->alamat;
        $siswa->gender = $request->gender;
        $siswa->agama = $request->agama;
        $siswa->usia = $request->usia;
        // $siswa->image = $image->hashName();

        $data = $siswa->save();

        if ($data) {
            return response()->json([
                'error' => false,
                'success' => 1,
                'message' => 'Data berhasil disimpan',
            ], 201); // Status code 201 untuk Created
        } else {
            return response()->json([
                'error' => true,
                'success' => 0,
                'message' => 'Data gagal disimpan',
            ], 500); // Status code 500 untuk Internal Server Error
        }
    }

    public function cari(Request $request)
    {
        $siswa = Siswa::where('namasiswa', 'like', '%' . $request->cari . '%')->get();

        return new SiswaResource(true, 'List Data Siswa', $siswa);
    }
}
