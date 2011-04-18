package net.sourceforge.jaad.mp4.api;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sourceforge.jaad.mp4.boxes.Box;
import net.sourceforge.jaad.mp4.boxes.BoxTypes;
import net.sourceforge.jaad.mp4.boxes.impl.CopyrightBox;
import net.sourceforge.jaad.mp4.boxes.impl.meta.ID3TagBox;
import net.sourceforge.jaad.mp4.boxes.impl.meta.ITunesMetadataBox;
import net.sourceforge.jaad.mp4.boxes.impl.meta.NeroMetadataTagsBox;

/**
 * This class contains the metadata for a movie. It parses different metadata
 * types (iTunes tags, ID3).
 * The fields can be read via the <code>get(Field)</code> method using one of
 * the predefined <code>Field</code>s.
 *
 * @author in-somnia
 */
public class MetaData {

	public static class Field<T> {

		public static final Field<String> ARTIST = new Field<String>();
		public static final Field<String> TITLE = new Field<String>();
		public static final Field<String> ALBUM_ARTIST = new Field<String>();
		public static final Field<String> ALBUM = new Field<String>();
		public static final Field<Integer> TRACK_NUMBER = new Field<Integer>();
		public static final Field<Integer> TOTAL_TRACKS = new Field<Integer>();
		public static final Field<Integer> DISK_NUMBER = new Field<Integer>();
		public static final Field<Integer> TOTAL_DISKS = new Field<Integer>();
		public static final Field<String> COMPOSER = new Field<String>();
		public static final Field<String> COMMENTS = new Field<String>();
		public static final Field<Integer> TEMPO = new Field<Integer>();
		public static final Field<Integer> LENGTH_IN_MILLISECONDS = new Field<Integer>();
		public static final Field<Date> RELEASE_DATE = new Field<Date>();
		public static final Field<String> GENRE = new Field<String>();
		public static final Field<String> ENCODER_NAME = new Field<String>();
		public static final Field<String> ENCODER_TOOL = new Field<String>();
		public static final Field<Date> ENCODING_DATE = new Field<Date>();
		public static final Field<String> COPYRIGHT = new Field<String>();
		public static final Field<String> PUBLISHER = new Field<String>();
		public static final Field<Boolean> COMPILATION = new Field<Boolean>();
		public static final Field<List<Artwork>> COVER_ARTWORK = new Field<List<Artwork>>();
		public static final Field<String> GROUPING = new Field<String>();
		public static final Field<String> LYRICS = new Field<String>();
		public static final Field<Integer> RATING = new Field<Integer>();
		public static final Field<Integer> PODCAST = new Field<Integer>();
		public static final Field<String> PODCAST_URL = new Field<String>();
		public static final Field<String> CATEGORY = new Field<String>();
		public static final Field<String> KEYWORDS = new Field<String>();
		public static final Field<Integer> EPISODE_GLOBAL_UNIQUE_ID = new Field<Integer>();
		public static final Field<String> DESCRIPTION = new Field<String>();
		public static final Field<String> TV_SHOW = new Field<String>();
		public static final Field<String> TV_NETWORK = new Field<String>();
		public static final Field<String> TV_EPISODE = new Field<String>();
		public static final Field<Integer> TV_EPISODE_NUMBER = new Field<Integer>();
		public static final Field<Integer> TV_SEASON = new Field<Integer>();
		public static final Field<String> INTERNET_RADIO_STATION = new Field<String>();
		public static final Field<String> PURCHASE_DATE = new Field<String>();
		public static final Field<String> GAPLESS_PLAYBACK = new Field<String>();
		public static final Field<Boolean> HD_VIDEO = new Field<Boolean>();
		public static final Field<Locale> LANGUAGE = new Field<Locale>();
		//sorting
		public static final Field<String> ARTIST_SORT_TEXT = new Field<String>();
		public static final Field<String> TITLE_SORT_TEXT = new Field<String>();
		public static final Field<String> ALBUM_SORT_TEXT = new Field<String>();

		private Field() {
		}
	}
	private static final String[] STANDARD_GENRES = {
		"undefined",
		//IDv1 standard
		"blues",
		"classic rock",
		"country",
		"dance",
		"disco",
		"funk",
		"grunge",
		"hip hop",
		"jazz",
		"metal",
		"new age",
		"oldies",
		"other",
		"pop",
		"r and b",
		"rap",
		"reggae",
		"rock",
		"techno",
		"industrial",
		"alternative",
		"ska",
		"death metal",
		"pranks",
		"soundtrack",
		"euro techno",
		"ambient",
		"trip hop",
		"vocal",
		"jazz funk",
		"fusion",
		"trance",
		"classical",
		"instrumental",
		"acid",
		"house",
		"game",
		"sound clip",
		"gospel",
		"noise",
		"alternrock",
		"bass",
		"soul",
		"punk",
		"space",
		"meditative",
		"instrumental pop",
		"instrumental rock",
		"ethnic",
		"gothic",
		"darkwave",
		"techno industrial",
		"electronic",
		"pop folk",
		"eurodance",
		"dream",
		"southern rock",
		"comedy",
		"cult",
		"gangsta",
		"top ",
		"christian rap",
		"pop funk",
		"jungle",
		"native american",
		"cabaret",
		"new wave",
		"psychedelic",
		"rave",
		"showtunes",
		"trailer",
		"lo fi",
		"tribal",
		"acid punk",
		"acid jazz",
		"polka",
		"retro",
		"musical",
		"rock and roll",
		//winamp extension
		"hard rock",
		"folk",
		"folk rock",
		"national folk",
		"swing",
		"fast fusion",
		"bebob",
		"latin",
		"revival",
		"celtic",
		"bluegrass",
		"avantgarde",
		"gothic rock",
		"progressive rock",
		"psychedelic rock",
		"symphonic rock",
		"slow rock",
		"big band",
		"chorus",
		"easy listening",
		"acoustic",
		"humour",
		"speech",
		"chanson",
		"opera",
		"chamber music",
		"sonata",
		"symphony",
		"booty bass",
		"primus",
		"porn groove",
		"satire",
		"slow jam",
		"club",
		"tango",
		"samba",
		"folklore",
		"ballad",
		"power ballad",
		"rhythmic soul",
		"freestyle",
		"duet",
		"punk rock",
		"drum solo",
		"a capella",
		"euro house",
		"dance hall"
	};
	private static final String[] NERO_TAGS = {
		"artist", "title", "album", "track", "totaltracks", "year", "genre",
		"disc", "totaldiscs", "url", "copyright", "comment", "lyrics",
		"credits", "rating", "label", "composer", "isrc", "mood", "tempo"
	};
	private Map<Field<?>, Object> contents;

	MetaData() {
		contents = new HashMap<Field<?>, Object>();
	}

	/*moov.udta.meta:
	-ilst
	-tags
	--meta (no container!)
	--tseg
	---tshd
	 */
	void parse(Box meta) {
		//standard boxes
		if(meta.hasChild(BoxTypes.COPYRIGHT_BOX)) {
			CopyrightBox cprt = (CopyrightBox) meta.getChild(BoxTypes.COPYRIGHT_BOX);
			put(Field.LANGUAGE, new Locale(cprt.getLanguageCode()));
			put(Field.COPYRIGHT, cprt.getNotice());
		}
		//if(meta.containsChild(BoxTypes.PRIMARY_ITEM_BOX)) pitm = (PrimaryItemBox) meta.getChild(BoxTypes.PRIMARY_ITEM_BOX);
		//if(meta.containsChild(BoxTypes.DATA_INFORMATION_BOX)) dinf = (ContainerBox) meta.getChild(BoxTypes.DATA_INFORMATION_BOX);
		//if(meta.containsChild(BoxTypes.ITEM_LOCATION_BOX)) iloc = (ItemLocationBox) meta.getChild(BoxTypes.ITEM_LOCATION_BOX);
		//if(meta.containsChild(BoxTypes.ITEM_PROTECTION_BOX)) ipro = (ItemProtectionBox) meta.getChild(BoxTypes.ITEM_PROTECTION_BOX);
		//if(meta.containsChild(BoxTypes.ITEM_INFORMATION_BOX)) iinf = (ItemInformationBox) meta.getChild(BoxTypes.ITEM_INFORMATION_ENTRY);
		//if(meta.hasChild(BoxTypes.IPMP_CONTROL_BOX));
		//id3, TODO: can be present in different languages
		if(meta.hasChild(BoxTypes.ID3_TAG_BOX)) parseID3((ID3TagBox) meta.getChild(BoxTypes.ID3_TAG_BOX));
		//itunes
		if(meta.hasChild(BoxTypes.ITUNES_META_LIST_BOX)) parseITunesMetaData(meta.getChild(BoxTypes.ITUNES_META_LIST_BOX));
		//nero tags
		if(meta.hasChild(BoxTypes.NERO_METADATA_TAGS_BOX)) parseNeroTags((NeroMetadataTagsBox) meta.getChild(BoxTypes.NERO_METADATA_TAGS_BOX));
	}

	private void parseITunesMetaData(Box ilst) {
		final List<Box> boxes = ilst.getChildren();
		long l;
		ITunesMetadataBox data;
		for(Box box : boxes) {
			l = box.getType();
			data = (ITunesMetadataBox) box.getChild(BoxTypes.ITUNES_METADATA_BOX);
			if(l==BoxTypes.ARTIST_NAME_BOX) put(Field.ARTIST, data.getText());
			else if(l==BoxTypes.TRACK_NAME_BOX) put(Field.TITLE, data.getText());
			else if(l==BoxTypes.ALBUM_ARTIST_NAME_BOX) put(Field.ALBUM_ARTIST, data.getText());
			else if(l==BoxTypes.ALBUM_NAME_BOX) put(Field.ALBUM, data.getText());
			else if(l==BoxTypes.TRACK_NUMBER_BOX) {
				byte[] b = data.getData();
				put(Field.TRACK_NUMBER, new Integer(b[7]));
				put(Field.TOTAL_TRACKS, new Integer(b[9]));
			}
			else if(l==BoxTypes.DISK_NUMBER_BOX) put(Field.DISK_NUMBER, data.getInteger());
			else if(l==BoxTypes.COMPOSER_NAME_BOX) put(Field.COMPOSER, data.getText());
			else if(l==BoxTypes.COMMENTS_BOX) put(Field.COMMENTS, data.getText());
			else if(l==BoxTypes.TEMPO_BOX) put(Field.TEMPO, data.getInteger());
			else if(l==BoxTypes.RELEASE_DATE_BOX) put(Field.RELEASE_DATE, data.getDate());
			else if(l==BoxTypes.GENRE_BOX||l==BoxTypes.CUSTOM_GENRE_BOX) {
				final String s;
				if(data.getDataType()==ITunesMetadataBox.DataType.UTF8) s = data.getText();
				else s = STANDARD_GENRES[data.getInteger()];
				put(Field.GENRE, s);
			}
			else if(l==BoxTypes.ENCODER_NAME_BOX) put(Field.ENCODER_NAME, data.getText());
			else if(l==BoxTypes.ENCODER_TOOL_BOX) put(Field.ENCODER_TOOL, data.getText());
			else if(l==BoxTypes.COPYRIGHT_BOX) put(Field.COPYRIGHT, data.getText());
			else if(l==BoxTypes.COMPILATION_PART_BOX) put(Field.COMPILATION, data.getBoolean());
			else if(l==BoxTypes.COVER_BOX) {
				if(contents.containsKey(Field.COVER_ARTWORK)) get(Field.COVER_ARTWORK).add(new Artwork(Artwork.Type.forDataType(data.getDataType()), data.getData()));
				else put(Field.COVER_ARTWORK, new ArrayList<Artwork>());
			}
			else if(l==BoxTypes.GROUPING_BOX) put(Field.GROUPING, data.getText());
			else if(l==BoxTypes.LYRICS_BOX) put(Field.LYRICS, data.getText());
			else if(l==BoxTypes.RATING_BOX) put(Field.RATING, data.getInteger());
			else if(l==BoxTypes.PODCAST_BOX) put(Field.PODCAST, data.getInteger());
			else if(l==BoxTypes.PODCAST_URL_BOX) put(Field.PODCAST_URL, data.getText());
			else if(l==BoxTypes.CATEGORY_BOX) put(Field.CATEGORY, data.getText());
			else if(l==BoxTypes.KEYWORD_BOX) put(Field.KEYWORDS, data.getText());
			else if(l==BoxTypes.DESCRIPTION_BOX) put(Field.DESCRIPTION, data.getText());
			else if(l==BoxTypes.LONG_DESCRIPTION_BOX) put(Field.DESCRIPTION, data.getText());
			else if(l==BoxTypes.TV_SHOW_BOX) put(Field.TV_SHOW, data.getText());
			else if(l==BoxTypes.TV_NETWORK_NAME_BOX) put(Field.TV_NETWORK, data.getText());
			else if(l==BoxTypes.TV_EPISODE_BOX) put(Field.TV_EPISODE, data.getText());
			else if(l==BoxTypes.TV_EPISODE_NUMBER_BOX) put(Field.TV_EPISODE_NUMBER, data.getInteger());
			else if(l==BoxTypes.TV_SEASON_BOX) put(Field.TV_SEASON, data.getInteger());
			else if(l==BoxTypes.PURCHASE_DATE_BOX) put(Field.PURCHASE_DATE, data.getText());
			else if(l==BoxTypes.GAPLESS_PLAYBACK_BOX) put(Field.GAPLESS_PLAYBACK, data.getText());
			else if(l==BoxTypes.HD_VIDEO_BOX) put(Field.HD_VIDEO, data.getBoolean());
			else if(l==BoxTypes.ARTIST_SORT_BOX) put(Field.ARTIST_SORT_TEXT, data.getText());
			else if(l==BoxTypes.TRACK_SORT_BOX) put(Field.TITLE_SORT_TEXT, data.getText());
			else if(l==BoxTypes.ALBUM_SORT_BOX) put(Field.ALBUM_SORT_TEXT, data.getText());
		}
	}

	private void parseID3(ID3TagBox box) {
		try {
			final DataInputStream in = new DataInputStream(new ByteArrayInputStream(box.getID3Data()));
			ID3Tag tag = new ID3Tag(in);
			int[] num;
			for(ID3Frame frame : tag.getFrames()) {
				switch(frame.getID()) {
					case ID3Frame.TITLE:
						put(Field.TITLE, frame.getEncodedText());
						break;
					case ID3Frame.ALBUM_TITLE:
						put(Field.ALBUM, frame.getEncodedText());
						break;
					case ID3Frame.TRACK_NUMBER:
						num = frame.getNumbers();
						put(Field.TRACK_NUMBER, num[0]);
						if(num.length>1) put(Field.TOTAL_TRACKS, num[1]);
						break;
					case ID3Frame.ARTIST:
						put(Field.ARTIST, frame.getEncodedText());
						break;
					case ID3Frame.COMPOSER:
						put(Field.COMPOSER, frame.getEncodedText());
						break;
					case ID3Frame.BEATS_PER_MINUTE:
						put(Field.TEMPO, frame.getNumber());
						break;
					case ID3Frame.LENGTH:
						put(Field.LENGTH_IN_MILLISECONDS, frame.getNumber());
						break;
					case ID3Frame.LANGUAGES:
						put(Field.LANGUAGE, frame.getLocale());
						break;
					case ID3Frame.COPYRIGHT_MESSAGE:
						put(Field.COPYRIGHT, frame.getEncodedText());
						break;
					case ID3Frame.PUBLISHER:
						put(Field.PUBLISHER, frame.getEncodedText());
						break;
					case ID3Frame.INTERNET_RADIO_STATION_NAME:
						put(Field.INTERNET_RADIO_STATION, frame.getEncodedText());
						break;
					case ID3Frame.ENCODING_TIME:
						put(Field.ENCODING_DATE, frame.getDate());
						break;
					case ID3Frame.RELEASE_TIME:
						put(Field.RELEASE_DATE, frame.getDate());
						break;
					case ID3Frame.ENCODING_TOOLS_AND_SETTINGS:
						put(Field.ENCODER_TOOL, frame.getEncodedText());
						break;
					case ID3Frame.PERFORMER_SORT_ORDER:
						put(Field.ARTIST_SORT_TEXT, frame.getEncodedText());
						break;
					case ID3Frame.TITLE_SORT_ORDER:
						put(Field.TITLE_SORT_TEXT, frame.getEncodedText());
						break;
					case ID3Frame.ALBUM_SORT_ORDER:
						put(Field.ALBUM_SORT_TEXT, frame.getEncodedText());
						break;
				}
			}
		}
		catch(IOException e) {
		}
	}

	private void parseNeroTags(NeroMetadataTagsBox tags) {
		final Map<String, String> pairs = tags.getPairs();
		String val;
		for(String key : pairs.keySet()) {
			val = pairs.get(key);
			try {
				if(key.equals(NERO_TAGS[0])) put(Field.ARTIST, val);
				if(key.equals(NERO_TAGS[1])) put(Field.TITLE, val);
				if(key.equals(NERO_TAGS[2])) put(Field.ALBUM, val);
				if(key.equals(NERO_TAGS[3])) put(Field.TRACK_NUMBER, Integer.parseInt(val));
				if(key.equals(NERO_TAGS[4])) put(Field.TOTAL_TRACKS, Integer.parseInt(val));
				if(key.equals(NERO_TAGS[5])) {
					Calendar c = Calendar.getInstance();
					c.set(Calendar.YEAR, Integer.parseInt(val));
					put(Field.RELEASE_DATE, c.getTime());
				}
				if(key.equals(NERO_TAGS[6])) put(Field.GENRE, val);
				if(key.equals(NERO_TAGS[7])) put(Field.DISK_NUMBER, Integer.parseInt(val));
				if(key.equals(NERO_TAGS[8])) put(Field.TOTAL_DISKS, Integer.parseInt(val));
				if(key.equals(NERO_TAGS[9])); //url
				if(key.equals(NERO_TAGS[10])) put(Field.COPYRIGHT, val);
				if(key.equals(NERO_TAGS[11])) put(Field.COMMENTS, val);
				if(key.equals(NERO_TAGS[12])) put(Field.LYRICS, val);
				if(key.equals(NERO_TAGS[13])); //credits
				if(key.equals(NERO_TAGS[14])) put(Field.RATING, Integer.parseInt(val));
				if(key.equals(NERO_TAGS[15])) put(Field.PUBLISHER, val);
				if(key.equals(NERO_TAGS[16])) put(Field.COMPOSER, val);
				if(key.equals(NERO_TAGS[17])); //isrc
				if(key.equals(NERO_TAGS[18])); //mood
				if(key.equals(NERO_TAGS[19])) put(Field.TEMPO, Integer.parseInt(val));
			}
			catch(NumberFormatException e) {
			}
		}
	}

	private <T> void put(Field<T> field, T value) {
		put(field, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Field<T> field) {
		return (T) contents.get(field);
	}
}
