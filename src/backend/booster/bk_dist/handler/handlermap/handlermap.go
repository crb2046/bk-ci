package handlermap

import (
	"fmt"

	dcType "build-booster/bk_dist/common/types"
	"build-booster/bk_dist/handler"
	"build-booster/bk_dist/handler/cc"
	"build-booster/bk_dist/handler/custom"
	"build-booster/bk_dist/handler/echo"
	"build-booster/bk_dist/handler/find"
	"build-booster/bk_dist/handler/tc"
	"build-booster/bk_dist/handler/ue4"
)

var handleMap map[dcType.BoosterType]func() (handler.Handler, error)

func init() {
	handleMap = map[dcType.BoosterType]func() (handler.Handler, error){
		dcType.BoosterCC:     cc.NewTaskCC,
		dcType.BoosterFind:   find.NewFinder,
		dcType.BoosterTC:     tc.NewTextureCompressor,
		dcType.BoosterUE4:    ue4.NewUE4,
		dcType.BoosterEcho:   echo.NewEcho,
		dcType.BoosterCustom: custom.NewCustom,
	}
}

// GetHandler return handle by type
func GetHandler(key dcType.BoosterType) (handler.Handler, error) {
	if v, ok := handleMap[key]; ok {
		return v()
	}

	// default handler
	return nil, fmt.Errorf("unknown handler %s", key)
}
