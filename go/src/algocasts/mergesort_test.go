package algocasts

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestMergeSortRecursive(t *testing.T) {
	tests := []struct {
		name  string
		input []int
		want  []int
	}{
		{"double", []int{2, 1}, []int{1, 2}},
		{"single", []int{1, 5, 4}, []int{1, 4, 5}},
		{"normal", []int{1, 9, 5, 3, 2, 6}, []int{1, 2, 3, 5, 6, 9}},
	}
	for _, tt := range tests {
		MergeSortRecursive(tt.input)
		t.Log(tt.input, tt.want)
		assert.Equal(t, tt.input, tt.want)
	}
}

func TestMergeSortIterative(t *testing.T) {
	tests := []struct {
		name  string
		input []int
		want  []int
	}{
		{"double", []int{2, 1}, []int{1, 2}},
		{"single", []int{1, 5, 4}, []int{1, 4, 5}},
		{"normal", []int{1, 9, 5, 3, 2, 6}, []int{1, 2, 3, 5, 6, 9}},
	}
	for _, tt := range tests {
		mergerSortIterative(tt.input)
		assert.Equal(t, tt.input, tt.want)
	}
}
