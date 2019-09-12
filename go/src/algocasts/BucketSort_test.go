package algocasts

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func gen100() []int {
	result := make([]int, 0, 100)
	for i := 1; i <= 100; i++ {
		result = append(result, i*100)
	}
	return result
}

func genR100() []int {
	result := make([]int, 0, 100)
	for i := 100; i > 0; i-- {
		result = append(result, i*100)
	}
	return result
}

func TestBucketSort(t *testing.T) {
	tests := []struct {
		name  string
		input []int
		want  []int
	}{
		{"sort100", genR100(), gen100()},
	}
	for _, tt := range tests {
		BucketSort(tt.input)
		assert.Equal(t, tt.input, tt.want)
	}
}
