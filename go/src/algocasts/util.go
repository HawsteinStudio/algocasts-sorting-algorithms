package algocasts

func swap(array []int, a int, b int) {
	tmp := array[b]
	array[b] = array[a]
	array[a] = tmp
}
