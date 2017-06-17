package com.grontol.mediacinta.items;

public class ItemSurat
{
	private String idSurat;
	private String namaSurat;
	
	public ItemSurat(String[] s)
	{
		this(s[0], s[1]);
	}
	
	public ItemSurat(String idSurat, String namaSurat)
	{
		this.idSurat = idSurat;
		this.namaSurat = namaSurat;
	}

	public String getIdSurat()
	{
		return idSurat;
	}

	public String getNamaSurat()
	{
		return namaSurat;
	}

	public void setIdSurat(String idSurat)
	{
		this.idSurat = idSurat;
	}

	public void setNamaSurat(String namaSurat)
	{
		this.namaSurat = namaSurat;
	}
}
