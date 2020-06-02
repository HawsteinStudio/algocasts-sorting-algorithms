package algocasts

func BucketSort(input []int) {
	if len(input) == 0 {
		return
	}

	BucketSize := 10
	min := input[0]
	max := input[0]
	for _, val := range input {
		if val > max {
			max = val
		}
		if val < min {
			min = val
		}
	}

	bucketCount := len(input) / BucketSize

	buckets := make([][]int, bucketCount)
	for i := 0; i < bucketCount; i++ {
		buckets[i] = make([]int, 0, BucketSize)
	}

	for _, num := range input {
		idx := int(((num - min) / (max - min + 1.0) * bucketCount))
		buckets[idx] = append(buckets[idx], num)
	}

	idx := 0
	for _, bucket := range buckets {
		insertsort(bucket)
		for _, num := range bucket {
			input[idx] = num
			idx += 1
		}
	}

}
