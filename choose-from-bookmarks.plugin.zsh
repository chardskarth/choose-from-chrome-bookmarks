
cachedChooseGui="$HOME/.cache/choose_from_bookmarks_cached_bookmarks"
outputLogs="$HOME/.logs/choose_from_bookmarks.log"
chromeBookmarksLocation="$HOME/Library/Application Support/Google/Chrome"


function _choose_from_bookmarks__refresh_bookmarks() {
	rm "$cachedChooseGui" &>> "$outputLogs"

}

function _choose_from_bookmarks__trigger_bookmarks() {
	local selected

	selected=$(cat "$cachedChooseGui" | choose -s 20 -m -n 20) 
	if [[ $selected == *" >> "* ]]; then
		selectedurl=$(echo "$selected" | awk -F " >> " '{print $2}')

		_choose_from_bookrmarks__open_in_chrome "$selectedurl"
	elif [[ "$selected" == "sprintstatusof"* ]]; then
		"${=selected}" && read abcde
	else
		[[ ! -z $selected ]] && "${=selected}"
	fi

}

function _choose_from_bookmarks__open_in_chrome() {
	open -a "Google Chrome.app" $@
}


function _choose_from_bookmarks__jq_read_bookmarks() {
	bookmarkfiles$(find "$chromeBookmarksLocation" -maxdept 2 -name "Bookmarks")
	
	echo "found $bookmarkfiles" >> $


}

