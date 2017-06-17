package com.grontol.mediacinta.items;

public class ItemAyat
{
	private String idAyat;
	private String idSurat;
	private String namaSurat;
	private String ayat;
	private String terjemah;
	private String ejaan;
	private String audio;
	
	public ItemAyat(String[] s)
	{
		this(s[0], s[1], s[2], s[3], s[4], s[5], s[6]);
	}
	
	public ItemAyat(String idAyat, String idSurat, String namaSurat, String ayat, String terjemah, String ejaan, String audio)
	{
		this.idAyat = idAyat;
		this.idSurat = idSurat;
		this.namaSurat = namaSurat;
		this.ayat = ayat;
		this.terjemah = terjemah;
		this.ejaan = ejaan;
		this.audio = audio;
	}

	public String getNamaSurat()
	{
		return namaSurat;
	}

	public void setNamaSurat(String namaSurat)
	{
		this.namaSurat = namaSurat;
	}

	public String getIdAyat()
	{
		return idAyat;
	}

	public String getIdSurat()
	{
		return idSurat;
	}

	public String getAyat()
	{
		return ayat;
	}

	public String getTerjemah()
	{
		return terjemah;
	}

	public String getEjaan()
	{
		return ejaan;
	}

	public String getAudio()
	{
		return audio;
	}

	public void setIdAyat(String idAyat)
	{
		this.idAyat = idAyat;
	}

	public void setIdSurat(String idSurat)
	{
		this.idSurat = idSurat;
	}

	public void setAyat(String ayat)
	{
		this.ayat = ayat;
	}

	public void setTerjemah(String terjemah)
	{
		this.terjemah = terjemah;
	}

	public void setEjaan(String ejaan)
	{
		this.ejaan = ejaan;
	}

	public void setAudio(String audio)
	{
		this.audio = audio;
	}
}
