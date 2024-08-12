<?php
namespace App\Models;

use Illuminate\Database\Eloquent\Casts\Attribute;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Siswa extends Model
{
    use HasFactory;

    protected $table = 'siswas'; // Pastikan nama tabel sesuai dengan blueprint migrasi

    protected $primaryKey = 'idsiswa'; // Jika primary key bukan 'id'

    public $incrementing = true; // Jika primary key adalah auto-incrementing

    protected $fillable = [
        'siswa',  
        'nisn',
        'ttl',
        'alamat',
        'gender',  
        'agama',
        'usia',
        'image',
    ];

    protected $casts = [
        'gender' => 'string',
    ];

    protected function image(): Attribute
    {
        return Attribute::make(
            get: fn($image) => $image ? asset('storage/siswa/'.$image) : null
        );
    }
}
